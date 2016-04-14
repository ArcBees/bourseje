package com.arcbees.bourseje.server.upload;

import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.tools.cloudstorage.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

public class CloudStorageUploadService implements ImageUploadService {
    private static final int BUFFER_SIZE = 2 * 1024 * 1024;
    private static final String BUCKET_NAME = "myBucket";

    private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
            .initialRetryDelayMillis(10)
            .retryMaxAttempts(10)
            .totalRetryPeriodMillis(15000)
            .build());

    @Override
    public String upload(String name, InputStream inputStream, long size) {
        GcsOutputChannel outputChannel;

        GcsFileOptions options = new GcsFileOptions.Builder()
                .mimeType("image/jpeg")
                .acl("full-control") //TODO: Check if this is the right access control type.
                .build();
        GcsFilename fileName = getFileName(name);

        try {
            outputChannel = gcsService.createOrReplace(fileName, options);
            copy(inputStream, Channels.newOutputStream(outputChannel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getServingUrl(fileName);
    }

    private GcsFilename getFileName(String name) {
        return new GcsFilename(BUCKET_NAME, name);
    }

    private String getServingUrl(GcsFilename fileName) {
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        String filename = String.format("/gs/%s/%s", fileName.getBucketName(), fileName.getObjectName());
        String servingUrl = imagesService.getServingUrl(ServingUrlOptions.Builder.withGoogleStorageFileName(filename));

        return servingUrl.replace("0.0.0.0", "localhost");
    }

    private void copy(InputStream input, OutputStream output) throws IOException {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = input.read(buffer);
            while (bytesRead != -1) {
                output.write(buffer, 0, bytesRead);
                bytesRead = input.read(buffer);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}

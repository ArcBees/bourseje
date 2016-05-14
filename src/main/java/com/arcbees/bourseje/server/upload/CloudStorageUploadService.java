/*
 * Copyright 2016 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.server.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

import org.apache.commons.io.FilenameUtils;

import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

public class CloudStorageUploadService implements ImageUploadService {
    private static final String[] IMAGE_FILE_EXTENSIONS = new String[]{"jpg", "png", "gif", "jpeg"};
    private static final String BUCKET_NAME = "jccqbourseje.appspot.com";
    private static final int BUFFER_SIZE = 2 * 1024 * 1024;

    private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
            .initialRetryDelayMillis(10)
            .retryMaxAttempts(10)
            .totalRetryPeriodMillis(15000)
            .build());

    @Override
    public String upload(String name, InputStream inputStream, long size) {
        GcsOutputChannel outputChannel;
        GcsFileOptions options = GcsFileOptions.getDefaultInstance();
        GcsFilename fileName = getFileName(name);

        verifyFileExtension(name);

        try {
            outputChannel = gcsService.createOrReplace(fileName, options);

            // IOUtils cause an INVALID_BLOB_KEY Exception
            copy(inputStream, Channels.newOutputStream(outputChannel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getServingUrl(fileName);
    }

    private void verifyFileExtension(String name) {
        String extension = FilenameUtils.getExtension(name);

        if (!containsExtension(extension)) {
            throw new RuntimeException("The provided file in not an image");
        }
    }

    private boolean containsExtension(String extension) {
        for (String allowedExtension : IMAGE_FILE_EXTENSIONS) {
            if (extension.toLowerCase().equals(allowedExtension)) {
                return true;
            }
        }

        return false;
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

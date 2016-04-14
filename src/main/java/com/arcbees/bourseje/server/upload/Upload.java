package com.arcbees.bourseje.server.upload;

import com.google.inject.Singleton;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.gae.AppEngineUploadAction;
import org.apache.commons.fileupload.FileItem;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Singleton
public class Upload extends AppEngineUploadAction {
    @Inject
    ImageUploadService imageUploadService;

    @Override
    public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
        FileItem fileItem = sessionFiles.get(0);
        InputStream inputStream;

        try {
            inputStream = fileItem.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String servingUrl = imageUploadService.upload(fileItem.getName(), inputStream, fileItem.getSize());
        removeSessionFileItems(request);

        return servingUrl;
    }
}

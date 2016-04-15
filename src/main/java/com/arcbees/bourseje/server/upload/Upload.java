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
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.google.inject.Singleton;

import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.gae.AppEngineUploadAction;

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

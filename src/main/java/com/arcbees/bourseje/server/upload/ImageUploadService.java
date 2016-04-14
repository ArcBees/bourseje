package com.arcbees.bourseje.server.upload;

import java.io.InputStream;

public interface ImageUploadService {
    String upload(String name, InputStream inputStream, long size);
}

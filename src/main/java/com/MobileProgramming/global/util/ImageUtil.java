package com.MobileProgramming.global.util;

import java.util.Base64;
public class ImageUtil {
    // Base64 디코딩 유틸리티 메서드 추가
    public static byte[] decodeBase64ToBytes(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }
}



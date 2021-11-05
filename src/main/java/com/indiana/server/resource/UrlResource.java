package com.indiana.server.resource;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlResource {
	private final String FCMINDIANTSERVICE = "/com/indiana/server/resource/indiant-2e0526f80ea2.json";
	public File getFcmIndiantService() throws URISyntaxException {
		URL urlService=getClass().getResource(FCMINDIANTSERVICE);
		File fileService=new File(urlService.toURI());
        return fileService;
    }
}

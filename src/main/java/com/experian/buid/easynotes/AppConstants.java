package com.experian.buid.easynotes;

/**
 * @author Marcos Godinho
 *
 */
public final class AppConstants {
	
	public static final String BASE_PKG = "com.experian.buid.easynotes";
	public static final String CONTROLLER_PKG = BASE_PKG + ".controller";

	public static final class Api {
		public static final String VERSION = "v1";
		public static final String TITLE = "Easy Notes API";
		public static final String DESCRIPTION = "Experian Easy Notes Spring Sample";
		
		public static final String BU_NAME = "buid";
		public static final String NAME = "easynotes";
		public static final String CONTEXT_PATH = "/" + BU_NAME + "/" + NAME;
		public static final String API_BASE_URL = "/" + VERSION;
		public static final String RESOURCE_ID = NAME;
	}
	
	public static final class Notes {
		public static final String NAME = "notes";
		public static final String PATH = Api.API_BASE_URL + "/" + NAME;
	}
}

package com.stroe.admin;

import com.jfinal.core.JFinal;

public class Application {
	
	public static void main(String[] args) {
        JFinal.start("WebContext", 8080, "/Stroe-admin");
	}

}

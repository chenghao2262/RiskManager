package com.riskmanager.demo;

public class HelloWorldAction {
	private String name;

    public String execute() throws Exception {
        System.out.println("debug1: execute");
        if ( getName().equals("") || getName() == null )
            return "error";

        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
         this.name = name;
     }
}

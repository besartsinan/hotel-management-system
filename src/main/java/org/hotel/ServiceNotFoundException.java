package org.hotel;

public class ServiceNotFoundException extends Exception{
    public ServiceNotFoundException (){
        super ("Service not foundd");
    }

    public ServiceNotFoundException (String message){
    super(message);
    }
}

package com.parasoft.examples.controller;

/**
 * An Exception that a Controller handler method can throw when the request is bad.
 */
@SuppressWarnings("serial")
public class BadRequestException
    extends Exception
{

    public BadRequestException(String message)
    {
        super(message);
    }

    public BadRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

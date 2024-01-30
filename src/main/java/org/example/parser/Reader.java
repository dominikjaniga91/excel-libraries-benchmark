package org.example.parser;

public interface Reader<T> {

    T readFile(String file);
}

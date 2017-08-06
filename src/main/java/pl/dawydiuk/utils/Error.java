package pl.dawydiuk.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Created by Konrad on 05.08.2017.
 */
@AllArgsConstructor
public class Error {


    @Getter
    private int code;


    @Getter
    private String message;
}

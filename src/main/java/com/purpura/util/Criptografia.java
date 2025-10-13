package com.purpura.util;

import org.mindrot.jbcrypt.BCrypt;

public class Criptografia {
    private static final int cost = 10;
    public static String criptografar(String senhaLimpa){
        String hash = BCrypt.hashpw(senhaLimpa, BCrypt.gensalt(cost));
        return hash;
    }
}

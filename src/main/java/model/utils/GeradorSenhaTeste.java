package model.utils;

public class GeradorSenhaTeste {
    public static void main(String[] args) {
        String senhaPura = "1234";
        
        // Use o método da sua classe PasswordEncryptor que gera o hash
        String hashGerado = PasswordEncryptor.hashPassword(senhaPura); 
        
        System.out.println("Cole esta hash no banco:");
        System.out.println(hashGerado);
    }
}
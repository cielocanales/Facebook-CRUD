package model.utils;

import java.sql.Connection;
import model.dao.MySQLConnectionFactory; 

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Connection conn = MySQLConnectionFactory.getConnection(); 
            if (conn != null) {
                System.out.println("Teste: Ligação à base de dados realizada com sucesso!");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Teste falhou. Erro ao estabelecer a ligação:");
            e.printStackTrace();
        }
    }
}
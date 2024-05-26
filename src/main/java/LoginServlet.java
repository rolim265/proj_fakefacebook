import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginServlet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String email;
        String senha;

        System.out.println("Digite seu e-mail:");
        email = scanner.nextLine(); // Captura o e-mail digitado pelo usuário

        System.out.println("Digite sua senha:");
        senha = scanner.nextLine(); // Captura a senha digitada pelo usuário

        // Configurações de conexão com o banco de dados MySQL
        String url = "jdbc:mysql://localhost:3306/face"; // URL do banco de dados
        String usuarioBD = "root"; // Usuário do banco de dados
        String senhaBD = ""; // Senha do banco de dados

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection(url, usuarioBD, senhaBD);

            // SQL para inserir os dados na tabela 'usuarios'
            String sql = "INSERT INTO usuarios (email, senha) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            // Executar a instrução SQL de inserção
            int linhasAfetadas = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Dados inseridos com sucesso no banco de dados!");
            } else {
                System.out.println("Falha ao inserir dados no banco de dados.");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do MySQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os objetos de conexão e statement no bloco finally
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package BD;

import Classes.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.omg.CORBA.SetOverrideType;

public class CadastroDAO{
    public void salvarCadastro(Login l){
        String sql = "INSERT INTO login(nome, CPF, data_nascimento, endereco, login, senha) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatment pstm = null;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatment(sql);

            pstm.setString(1, l.nome);
            pstm.setString(2, l.CPF);
            pstm.setString(3, l.data_nascimento);
            pstm.setString(4, l.endereco);
            pstm.setString(5, l.login);
            pstm.setString(6, l.senha);

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro cadastrado: " + e);
        } finally {
            try{
                if(pstm != null)
                    pstm.close();

                if(conn != null)
                    conn.close();

            } catch (SQLExeption e){
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar, encerrar conexão: " + e);
            }
        }
    }

    public boolean autenticaUsuarioBool (Login login) throws Exception{
        String sql = "Select * from login where login = ? and senha = ?";

        Connection conn = null;
        PreparedStatment pstm = null;
        boolean resp = false;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatment(sql);

            pstm.setString(1, login.login);
            pstm.setString(2, login.senha);

            ResultSet result = pstm.executeQuery();
            if(result.next())
              resp = true;
    
        } catch (SQLExeption e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e);
        }
        return resp;
    }

    public Login autenticaUsuario (Login login) throws Exception{
        String sql = "Select * from login where login = ? and senha = ?";

        Connection conn = null;
        PreparedStatment pstm = null;
        Login resp = new Login();

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatment(sql);

            pstm.setString(1, login.login);
            pstm.setString(2, login.senha);

            ResultSet result = pstm.executeQuery();
            while(result.next()){
                resp.nome = result.getString("nome");
                resp.login = result.getString("login");
                resp.senha = result.getString("senha");
            }
        } catch (SQLExeption e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e);
        }
        return resp;
    }

    public Login buscaUsuario(Login login) throws Exception{
        String sql = "Select * from login where login = ?";
        
        Connection conn = null;
        PreparedStatment pstm = null;
        Login resp = new Login();

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatment(sql);

            pstm.setString(1, login.login);

            ResultSet result = pstm.executeQuery();
            while(result.next()){
                resp.nome = result.getString("nome");
                resp.CPF = result.getString("CPF");
                resp.data_nascimento = result.getString("data_nascimento");
                resp.endereco = result.getString("endereco");
                resp.login = result.getString("login");
                resp.senha = result.getString("senha");
            }
        } catch (SQLExeption e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e);
        }
        return resp;
    }

    public void atualizaLogin(Login login){
        String sql = "Update login set nome = ?, data_nascimento = ?, endereco = ?";

        Connection conn = null;
        PreparedStatment pstm = null;

        try{
            conn = ConnectionFactory.criarConexao()/
            
            if(login.senha = null)
                sql += "Where login = ?";
            else 
                sql += ", senha = ? where login = ?";
            
            pstm = conn.prepareStatment(sql);

            pstm.getString(1, login.nome);
            pstm.getString(2, login.data_nascimento);
            pstm.getString(3. login.endereco);

             if(login.senha = null)
                  pstm.getString(4, login.login);
             else{
                  pstm.getString(4, login.senha);
                  pstm.getString(5, login.login);
             }

             pstm.execute();

             JOptionPane.showMessageDialog(null, "Dados atualizados concluida");
        } catch (Exception e){
             JOptionPane.showMessageDialog(null, "Erro ao atualizar" + e);
        } finally {
            try{
                if(conn != null)
                   conn.close();

                if(pstm != null)
                   pstm.close(); 
            }
        } catch (SQLExeption e) {
            JOptionPane.showMessageDialog("Erro ao atualizar, encerrar conexão" + )
        }
    }

    public void deletarUsuario (Login login){
        String sql = "Delete from login where login = ?";

        Connection conn = null;
        PreparedStatment pstm = null;

        try{
            conn = ConnectionFactory.criarConexao();
            pstm = conn.prepareStatment(sql);

            pstm.setString(1, login.login);

            pstm.execute();

            JOptionPane.showMessageDialog(null, "Usuario deletado");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao deletar o usuario" + e);
        } finally {
            try{
                if(conn != null)
                    conn.close();

                if(pstm != null)
                    pstm.close();
            } catch(SQLExeption e){
                JOptionPane.showMessageDialog(null, "Erro ao deletar o usuario ou encerrar a conexão" + e)
            }
        }
    }
}
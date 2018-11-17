package modelDAO;

import model.Usuario;
import java.sql.*;


public class CadastroDAO {

    public static Usuario cadastrar(Usuario user) {
        
        //Usuario user = new Usuario();
        
        try {
            // Conexão com banco de dados
            Connection con = Conecta.getConexao();
            
            // Sintaxe para inserir os dados no banco, tem 15
            String sql = "INSERT INTO usuario(cpf_cnpj, tipoPessoa, dataNascimento, sexo, email, telefone, nomeRazao, cep, logradouro, numero, complemento, estado, cidade, bairro, senha, Nivel_Usuario, Ativo, banco, agencia, conta, digito) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(sql);

            // TODO ID ficou auto incremente
            //ps.setInt(1, user.getId());
            ps.setString(1, user.getCpf_cnpj());
            ps.setInt(2, user.getTipoPessoa());
            ps.setDate(3, user.getDataNascimento());
            ps.setString(4, user.getSexo());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getTelefone());
            ps.setString(7, user.getNomeRazao());
            ps.setString(8, user.getCep());
            ps.setString(9, user.getLogradouro());
            ps.setString(10, user.getNumero());
            ps.setString(11, user.getComplemento());
            ps.setString(12, user.getEstado());
            ps.setString(13, user.getCidade());
            ps.setString(14, user.getBairro());
            ps.setString(15, user.getSenha());
            ps.setString(16, user.getNivel_usuario());
            ps.setString(17, user.getAtivo());
            ps.setString(18, user.getBanco());
            ps.setInt(19, user.getAgencia());
            ps.setInt(20, user.getConta());
            ps.setInt(21, user.getDigito());
            
            ps.execute();
            
            ps.close();
            con.close();
            
            //out.println("Cadastro realizado !");

        } catch (Exception e) {
            e.printStackTrace();
            
        }

        return user;
    }
    
    public static Usuario getCpfCnpj(String cpfcnpj) {
        
        Usuario usuario = new Usuario();
        
        try {
            Connection con = Conecta.getConexao();
            String sql = "SELECT * FROM usuario WHERE cpf_cnpj=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpfcnpj);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                usuario.setCpf_cnpj(rs.getString("cpf_cnpj"));

            }else{
                usuario = null;
            }
            
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }
       
}
package controller;

import model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ItemCart;
import modelDAO.ProdutoDAO;

/**
 *
 * @author Caio
 */
@WebServlet(name = "ComprarServlet", urlPatterns = {"/ComprarServlet"})
public class ComprarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String resp = "";
            int codLivro = Integer.parseInt(request.getParameter("livro"));
            int codProduto = ProdutoDAO.getProduto(codLivro).getId();
            int codVendedor = ProdutoDAO.getProduto(codLivro).getId_usuario();
            
            HashMap<Integer, Integer> lista = new HashMap<>();
            Cookie[] cookies = request.getCookies();
            Cookie actualCookie = new Cookie("ShoppingCart", lista.toString());
            
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("ShoppingCart".equals(cookie.getName())) {
                        String value = cookie.getValue();
                        value = value.substring(1, value.length()-1);
                        String[] keyValuePairs = value.split(",");
                        actualCookie = cookie;
                        actualCookie.setMaxAge(60 * 60 * 24 * 7);
                        
                        if (value.length() > 0){
                            for(String pair : keyValuePairs) {
                                String[] entry = pair.split("=");
                                lista.put(Integer.parseInt(entry[0].trim()), Integer.parseInt(entry[1].trim()));
                            }
                        }
                    }
                }
            }

            if (codProduto > 0) {
                boolean produtoValido = true;

                for(Map.Entry<Integer, Integer> entry : lista.entrySet()) {
                    
                    if (lista.get(entry.getKey()) != null) {
                        int vendedorListado = ProdutoDAO.getProduto(entry.getKey()).getId_usuario();
                        int novoVendedor = ProdutoDAO.getProduto(codProduto).getId_usuario();
                        if (vendedorListado != novoVendedor) {
                            produtoValido = false;
                        }
                    }
                }
                
                if (produtoValido) {
                    lista = new Cart().AddItemCart(codProduto, lista);
                    resp = "ok";
                } else {
                    resp = "invalido";
                }
            } else {
                resp = "erro";
            }

            actualCookie.setValue(lista.toString());
            actualCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(actualCookie);
            
            out.print(resp);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

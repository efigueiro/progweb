package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignoServlet
 */

@WebServlet("/signoController")
public class SignoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recebendo a data de nascimento do formulário
        String dataNascimento = request.getParameter("dataNascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parse a data de nascimento
            Date data = (Date) sdf.parse(dataNascimento);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);

            // Carregar o arquivo XML dos signos
            File xmlFile = new File(getServletContext().getRealPath("/signos.xml"));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = (Document) builder.parse(xmlFile);

            // Criar XPath
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            NodeList signos = (NodeList) xpath.evaluate("/signos/signo", doc, XPathConstants.NODESET);

            for (int i = 0; i < signos.getLength(); i++) {
                Element signo = (Element) signos.item(i);

                // Extrair as datas de início e fim para o signo
                String dataInicio = ((org.w3c.dom.Document) signo).getElementsByTagName("dataInicio").item(0).getTextContent();
                String dataFim = ((org.w3c.dom.Document) signo).getElementsByTagName("dataFim").item(0).getTextContent();

                // Convertendo as datas de início e fim para formato Date
                Date inicio = (Date) sdf.parse(dataInicio + "/" + calendar.get(Calendar.YEAR));
                Date fim = (Date) sdf.parse(dataFim + "/" + calendar.get(Calendar.YEAR));

                // Ajustar para o próximo ano se a data de fim for no início do ano seguinte
                if (inicio.after(fim)) {
                    fim = (Date) sdf.parse(dataFim + "/" + (calendar.get(Calendar.YEAR) + 1));
                }

                // Verificar se a data de nascimento está dentro do intervalo do signo
                if ((calendar.getTime().equals(inicio) || calendar.getTime().after(inicio)) &&
                        (calendar.getTime().equals(fim) || calendar.getTime().before(fim))) {
                    // Encontrou o signo correspondente, enviar para a página JSP
                    String signoNome = ((org.w3c.dom.Document) signo).getElementsByTagName("signoNome").item(0).getTextContent();
                    String descricao = ((org.w3c.dom.Document) signo).getElementsByTagName("descricao").item(0).getTextContent();

                    // Atribuir os valores ao request para mostrar na página JSP
                    request.setAttribute("signoNome", signoNome);
                    request.setAttribute("descricao", descricao);

                    // Redirecionar para a página JSP para mostrar os resultados
                    RequestDispatcher dispatcher = request.getRequestDispatcher("resultado.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }

            // Se não encontrar nenhum signo válido
            request.setAttribute("erro", "Data de nascimento inválida para os signos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar a data.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
    }
		

}

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import static desafios.aula1.terminalEnfeitado.prettyPrint;
import static desafios.aula2.criadorDeDiretorios.criarDiretorio;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            String nota = filme.get("imDbRating");

            InputStream inputStream = new URL(urlImagem).openStream();
//            String nomeArquivo = titulo + ".png";
            String nomeArquivo = criarDiretorio("saida", titulo, urlImagem);

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(prettyPrint("titulo", titulo));
            System.out.println(prettyPrint("", urlImagem));
            System.out.println(prettyPrint("Nota", nota));
            System.out.println();
        }
    }
}

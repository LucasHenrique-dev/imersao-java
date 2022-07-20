package desafios.aula2;

import java.io.File;

public class criadorDeDiretorios {

    public static String criarDiretorio(String diretorio, String titulo, String imgLink) {
        File arquivoDiretorio = new File(diretorio);
        String extensao = coletarExtensao(imgLink);

        if (arquivoDiretorio.mkdir()) System.out.println("Diretorio criado com sucesso!\n");

        titulo = formatarTitulo(titulo);

        return diretorio + File.separator + titulo + extensao;
    }

    private static String formatarTitulo(String titulo) {
        String[] infosTitulo = titulo.toLowerCase().replace(":", " ").split(" ");

        return String.join("_", infosTitulo);
    }

    private static String coletarExtensao(String imgLink) {
        int index = imgLink.lastIndexOf(".");

        return imgLink.substring(index);
    }
}

package com.mobile.app;

/**
 * Created by Mrluke on 20/09/2016.
 */

public class AppConfiguracao {

    public static String ipCasa = "192.168.25.5:80";
    public static String ipFa7 = "10.54.107.175:80";
    //public static String ipHost = "127.0.0.1:80";

    public static String URL_LOGIN = "http://" + ipCasa + "/habitatpetWs/serverside/loginUsuario.php";
    public static String URL_CADASTRO = "http://" + ipCasa + "/habitatpetWs/serverside/cadastroUsuarios.php";
    public static String URL_PEIXES = "http://" + ipCasa + "/habitatpetWs/serverside/exibirPeixesJson.php";
    public static String URL_PLANTAS = "http://" + ipCasa+ "/habitatpetWs/serverside/exibirPlantasJson.php";
    public static String URL_PRODUTOS = "http://" + ipCasa + "/habitatpetWs/serverside/exibirProdutosJson.php";
}

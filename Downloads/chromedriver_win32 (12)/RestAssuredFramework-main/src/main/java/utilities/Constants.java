package src.main.java.utilities;


public class Constants {

    public int iHTTPCode200(){
        return Integer.parseInt(System.getProperty("ok"));
    }
    public String sHTTP200StatusLine() {
        return System.getProperty("ok.status.line");
    }
    public int iHTTPCode201(){
        return Integer.parseInt(System.getProperty("created"));
    }
    public String sHTTP201StatusLine(){
        return System.getProperty("created.status.line");
    }
    public int iHTTPCode400 (){
        return Integer.parseInt(System.getProperty("bad.request"));
    }
    public String sHTTP400StatusLIne(){
        return System.getProperty("bad.request.status.line");
    }
    public int iHTTPCode203(){
        return Integer.parseInt(System.getProperty("non.informative.info"));
    }
    public String sHTTP203StatusLine(){
        return System.getProperty("non.informative.info.status.line");
    }
    public int iHTTPCode404(){
        return Integer.parseInt(System.getProperty("page.not.found"));
    }
    public String sHTTP404StatusLine(){
        return System.getProperty("page.not.found.status.line");
    }
    public int iHTTPCode401(){
        return Integer.parseInt(System.getProperty("unauthorized"));
    }
    public String sHTTP401StatusLine(){
        return System.getProperty("unauthorized.status.line");
    }

    public int iHTTPCode405(){
        return Integer.parseInt(System.getProperty("not.allowed"));
    }
    public String sHTTP405StatusLine(){
        return System.getProperty("not.allowed.status.line");
    }

    public int iHTTPCode418(){
        return Integer.parseInt(System.getProperty("i.am.a.teapot"));
    }

    public String sHTTP418(){
        return System.getProperty("i.am.a.teapot.status.line");
    }
    public int iHTTP403(){
        return Integer.parseInt(System.getProperty("forbidden"));
    }
    public String sHTTP403StatusLine(){
        return System.getProperty("forbidden.status.line");
    }

    public int iHTTPCode500(){
        return Integer.parseInt(System.getProperty("internal.server"));
    }
    public String sHTTP500StatusLine(){
        return System.getProperty("internal.server.status.line");
    }
}

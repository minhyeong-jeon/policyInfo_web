/*
package com.policyInfo.policyInfo.workAPI;

import com.ctc.wstx.util.ExceptionUtil;
import lombok.NonNull;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkAPIController {


    @GetMapping("/apitest")
    public String callApiWithXml(@NonNull WantedList wantedList) {

        StringBuffer result = new StringBuffer();
        String resultData;
        try {
            */
/*String apiUrl = "https://apis.data.go.kr/B554287/NationalWelfareInformations/NationalWelfarelist?" +
                    "serviceKey=CBcCuS23DUJC6B2TfVraEjO8ReHrJF%2FoZfCEn3NxEiKhWB2Of81t%2B9At922MLrK6%2FwDNrVgIE35dMSFpUVKHXA%3D%3D" +
                    "&callTp=L&srchKeyCode=001";*//*



            StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B554287/NationalWelfareInformations/NationalWelfarelist"); */
/*URL*//*

            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=CBcCuS23DUJC6B2TfVraEjO8ReHrJF%2FoZfCEn3NxEiKhWB2Of81t%2B9At922MLrK6%2FwDNrVgIE35dMSFpUVKHXA%3D%3D"); */
/*Service Key*//*

            urlBuilder.append("&" + URLEncoder.encode("callTp","UTF-8") + "=" + URLEncoder.encode(wantedList.callTp, "UTF-8")); */
/*호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)*//*

            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(wantedList.pageNo, "UTF-8")); */
/*기본값 1, 최대 1000 검색 시작위치를 지정할 수 있습니다*//*

            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(wantedList.numOfRows, "UTF-8")); */
/*출력건수, 기본값 10, 최대 500 까지 가능합니다.*//*

            urlBuilder.append("&" + URLEncoder.encode("srchKeyCode","UTF-8") + "=" + URLEncoder.encode(wantedList.srchKeyCode, "UTF-8")); */
/*001 제목 002 내용 003 제목+내용*//*


            if(!wantedList.searchWrd.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode(wantedList.searchWrd, "UTF-8")); */
/*검색어*//*

            if(!wantedList.lifeArray.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("lifeArray","UTF-8") + "=" + URLEncoder.encode(wantedList.lifeArray, "UTF-8")); */
/*생애주기*//*

            if(!wantedList.charTrgterArray.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("charTrgterArray","UTF-8") + "=" + URLEncoder.encode(wantedList.charTrgterArray, "UTF-8")); */
/*대상특성*//*

            if(!wantedList.obstKiArray.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("obstKiArray","UTF-8") + "=" + URLEncoder.encode(wantedList.obstKiArray, "UTF-8")); */
/*장애유형*//*

            if(!wantedList.obstAbtArray.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("obstAbtArray","UTF-8") + "=" + URLEncoder.encode(wantedList.obstAbtArray, "UTF-8")); */
/*장애정도*//*

            if(!wantedList.trgterIndvdlArray.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("trgterIndvdlArray","UTF-8") + "=" + URLEncoder.encode(wantedList.trgterIndvdlArray, "UTF-8")); */
/*대상자*//*

            if(!wantedList.desireArray.isEmpty())
                urlBuilder.append("&" + URLEncoder.encode("desireArray","UTF-8") + "=" + URLEncoder.encode(wantedList.desireArray, "UTF-8")); */
/*시업목적*//*


            System.out.println("urlBuilder = " + urlBuilder);

            URL url = new URL(urlBuilder.toString());
            //System.out.println("apiUrl = " + apiUrl);
            //System.out.println("url = " + url);
            if (url.getProtocol().equals("https")) {    //해당 url이 https이면
                httpsGet(String.valueOf(url));            //ssl인증서를 무시하는 함수를 호출한다.
            }


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type","application/xml");
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            */
/*SAXBuilder builder = new SAXBuilder();

            Document document = builder.build(urlConnection.getInputStream());

            Element root = document.getRootElement();
            //루트의 자식!이니까 child를 얻어야한다!
            Element body = root.getChild("servList");
            //body안에 있는 items라는 엘리먼트를 얻어야한다.
            //Element items = body.getChild("servList");
            //items안에있는 자식들을 가져오자 ! 여러개니까 리스트여야겠지 ? 타입은 엘리먼트 !!
            List<Element> items = body.getChildren("servList");

            PublicDataList[] ar = new PublicDataList[items.size()];


           int i = 0;
            for(Element item : items) {

                String jurMnofNm = item.getChildText("jurMnofNm");
                String lifeArray = item.getChildText("lifeArray");
                String servDgst = item.getChildText("servDgst");
                String servDtlLink = item.getChildText("servDtlLink");
                String servNm = item.getChildText("servNm");
                String trgterIndvdlArray = item.getChildText("trgterIndvdlArray");
                String servID = item.getChildText("servID");

                PublicDataList vo = new PublicDataList(jurMnofNm, lifeArray, servDgst, servDtlLink, servNm, trgterIndvdlArray, servID);

                ar[i++] = vo;
            }*//*


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            //result.append("<xmp>");
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //result에 <xmp> </xmp>를 처음과 끝에 달아 준 것은 xml형태 그대로를 브라우저에서 출력해보기 위함
        return result +"";
    }

    public static String httpsGet(String strURL) throws Exception

    {
        URL url = null;
        HttpsURLConnection con = null;
        String ret = new String();

        try {
            url = new URL(strURL);
            ignoreSsl();
            con = (HttpsURLConnection)url.openConnection();


            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input = null;

            while ((input = br.readLine()) != null){
                ret += input;
            }

            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return ret;

    }

    public static void ignoreSsl() throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {

                return true;
        }
    };
    trustAllHttpsCertificates();
    HttpsURLConnection.setDefaultHostnameVerifier(hv);

    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }


}
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed_xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author julio.nava
 */
public class Main {
    
    public static void main(String[] args){
        
        //leerArchivoXML();
        
        armarGuardarXML();
    }
    
    public static void leerArchivoXML(){
        File file = new File("coches.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(file);
            
            //getDocumentElement()	
            //Accede al nodo raíz del documento
            // estos métodos podemos usarlos combinados para normalizar el archivo XML
            doc.getDocumentElement().normalize();
            
            System.out.println("Elemento Raiz: concesario");
            
            // almacenamos los nodos para luego mostrar la
            // cantidad de ellos con el método getLength()
            NodeList nList = doc.getElementsByTagName("coche");
            System.out.println("Numero de coches: " + nList.getLength());
            
            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("\nCoche id: " + eElement.getAttribute("id"));
                    System.out.println("Marca: " + eElement.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Modelo: " + eElement.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println("Cilindrada: " + eElement.getElementsByTagName("cilindrada").item(0).getTextContent());
                }
            }
            
            
            
            System.out.println("------------------------------------------------------");
            System.out.println("Leer la estructura cuando no se conoce");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if(eElement.hasChildNodes()) {
                        NodeList nl = node.getChildNodes();
                        for(int j=0; j<nl.getLength(); j++) {
                            Node nd = nl.item(j);
                            System.out.print(nd.getTextContent());
                        }
                    }
                }
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void armarGuardarXML() {
        try {
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            
            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("concesionario");
            doc.appendChild(eRaiz);
            
            // definimos el nodo que contendrá los elementos
            Element eCoche = doc.createElement("coche");
            eRaiz.appendChild(eCoche);
            
            // atributo para el nodo coche
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            eCoche.setAttributeNode(attr);
            
            // definimos cada uno de los elementos y le asignamos un valor
            Element eMarca = doc.createElement("marca");
            eMarca.appendChild(doc.createTextNode("Renault"));
            eCoche.appendChild(eMarca);

            Element eModelo = doc.createElement("modelo");
            eModelo.appendChild(doc.createTextNode("Megano"));
            eCoche.appendChild(eModelo);

            Element eCilindrada = doc.createElement("cilindrada");
            eCilindrada.appendChild(doc.createTextNode("1.5"));
            eCoche.appendChild(eCilindrada);
            
            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ejercicio.xml"));

            transformer.transform(source, result);
            
            System.out.println("Finalizado archivo ejercicio.xml generado");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}

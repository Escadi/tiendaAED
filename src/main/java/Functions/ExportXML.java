package Functions;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import Class.*;
import Models.ModelPedidos;
import javafx.scene.control.Alert;


public class ExportXML {

    public void ExportPedidos() {
         List<Pedidos> pedidosList = ModelPedidos.getPedidos();

        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        try {

            XMLEventWriter fileXMLPedidos = outputFactory.createXMLEventWriter(new FileOutputStream("Pedidos.xml"));
            XMLEvent event = eventFactory.createStartDocument();
            fileXMLPedidos.add(event);

            event = eventFactory.createStartElement("", "", "Pedidos");
            fileXMLPedidos.add(event);

            for (Pedidos pedidos : pedidosList){
                event = eventFactory.createStartElement("", "", "Pedido");
                fileXMLPedidos.add(event);

                event = eventFactory.createAttribute("id", String.valueOf(pedidos.getId()));
                fileXMLPedidos.add(event);

                event = eventFactory.createStartElement("","","usuario_id");
                fileXMLPedidos.add(event);
                event = eventFactory.createCharacters(String.valueOf(pedidos.getIdUsuario()));
                fileXMLPedidos.add(event);
                fileXMLPedidos.add(eventFactory.createEndElement("", "", "usuario_id"));


                event = eventFactory.createStartElement("","","producto_id");
                fileXMLPedidos.add(event);
                event = eventFactory.createCharacters(String.valueOf(pedidos.getIdProducto()));
                fileXMLPedidos.add(event);
                fileXMLPedidos.add(eventFactory.createEndElement("", "", "producto_id"));


                event = eventFactory.createStartElement("","","cantidad");
                fileXMLPedidos.add(event);
                event = eventFactory.createCharacters(String.valueOf(pedidos.getCantidad()));
                fileXMLPedidos.add(event);
                fileXMLPedidos.add(eventFactory.createEndElement("", "", "cantidad"));



                event = eventFactory.createStartElement("","","fecha");
                fileXMLPedidos.add(event);
                event = eventFactory.createCharacters(String.valueOf(pedidos.getFecha()));
                fileXMLPedidos.add(event);
                fileXMLPedidos.add(eventFactory.createEndElement("", "", "fecha"));


                fileXMLPedidos.add(eventFactory.createEndElement("", "", "Pedido"));
            }

            fileXMLPedidos.add(eventFactory.createEndElement("", "", "Pedidos"));
            fileXMLPedidos.add(eventFactory.createEndDocument());
            fileXMLPedidos.flush();
            fileXMLPedidos.close();

        } catch (XMLStreamException e) {

        } catch (FileNotFoundException e) {
        }

    }
}

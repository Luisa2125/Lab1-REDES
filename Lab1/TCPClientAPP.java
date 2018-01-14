//librerias a usar
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
// clase clinte TCP
// Luisa FErnanda Arboleda
public class TCPClientAPP{

	public static void main(String argv[]) {
		//se le lo que ingresa el cliente 
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		Socket socket;
		//creamos el socket
		String mensaje="";
		try {
			//se incializa el socket con la direccion IP y el puerto a donde se va a enviar el mensaje del servidor
			socket = new Socket("127.0.0.1",6000);
			System.out.println("Hola Cliente TCP");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Cualquier mensajes que ingreses, el servidor te lo reenviara en mayusculas");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Escribe tu mensaje");
			//el objeto dataoutputStrem nos sirve para enviar mensajes al servidor
			DataOutputStream out =new DataOutputStream(socket.getOutputStream());
			//el objeto datainputstream nos sirve para leer mensajes que nos envia el servidor
			DataInputStream upperMen = new DataInputStream(socket.getInputStream());
			do {
				//se guarda el mensaje escrito
				mensaje = teclado.readLine();
				//se envia al servidor
				out.writeUTF(mensaje);
				//en caso del cliente querer salir solo presiona 1 y esta listo
				if(mensaje.equals("1")){
					System.out.println("adios");
					socket.close();
					System.exit(1);
				}
				String upper ="";
				//se lee lo que envio el servidor
				upper = upperMen.readUTF();
				//se imprime el mensaje que envia el servidor
				System.out.println("El server envia: "+upper);
			} while (!mensaje.startsWith("fin"));
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}

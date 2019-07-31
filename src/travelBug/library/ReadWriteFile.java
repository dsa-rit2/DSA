package travelBug.library;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadWriteFile<T> {
	private T anyObject = null;
	private Class<T> classObject = null;
	private LinkArray<T> linkArrayObject = null;
	private ObjectInputStream inputStream = null;
	private FileInputStream fileInput = null;
	private ObjectOutputStream outputStream = null;
	private FileOutputStream fileOutput = null;
	private String fileName = null;

	public ReadWriteFile(String fileName, Class<T> oc) {
		this.fileName = fileName;
		this.classObject = oc;
	}

	@SuppressWarnings("unchecked")
	public T read() {
		try {
			fileInput = new FileInputStream(fileName);
			inputStream = new ObjectInputStream(fileInput);
			
			if (fileInput.available() != 0)
				anyObject = (T) inputStream.readObject();
			
			inputStream.close();
			fileInput.close();
		} catch (EOFException e) {
			try {
				anyObject = classObject.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			try {
				this.write(classObject.newInstance());
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return anyObject;
	}

	@SuppressWarnings("unchecked")
	public LinkArray<T> readLinkArray() {
		try {
			fileInput = new FileInputStream(fileName);
			inputStream = new ObjectInputStream(fileInput);
			if (fileInput.available() != 0) {
				linkArrayObject = (LinkArray<T>) inputStream.readObject();
			}
			inputStream.close();
			fileInput.close();
		} catch (EOFException e) {
			linkArrayObject = new LinkArray<T>();
		} catch (FileNotFoundException e) {
			this.writeLinkArray(new LinkArray<T>());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return linkArrayObject;
	}
	
	public void write(T obj) {
		try {
			fileOutput = new FileOutputStream(fileName);
			outputStream = new ObjectOutputStream(fileOutput);
			outputStream.writeObject(obj);
			outputStream.close();
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeLinkArray(LinkArray<T> arrayObj) {
		try {
			fileOutput = new FileOutputStream(fileName);
			outputStream = new ObjectOutputStream(fileOutput);
			outputStream.writeObject(arrayObj);
			outputStream.close();
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

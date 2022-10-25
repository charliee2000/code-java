package com.charlie.zipfile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

import com.charlie.zipfile.vo.ZipAttachmentVO;

public class ZipFileDemo {

	public static void main(String[] args) throws IOException {
		zipMultipleFiles();
//		zipMultipleByte();
	}

	public static byte[] zipMultipleByte() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		List<ZipAttachmentVO> mdFiles = demoZipAttachmentVoList();
		for (ZipAttachmentVO vo : mdFiles) {
			ZipEntry entry = new ZipEntry(vo.getFileName());
			entry.setSize(vo.getAttachment().length);
			zos.putNextEntry(entry);
			zos.write(vo.getAttachment());
		}
		zos.closeEntry();
		zos.close();

		byte[] zipFileByteArray = baos.toByteArray();
		File tempFile = new File("demo2.zip");
		FileUtils.writeByteArrayToFile(tempFile, zipFileByteArray);
		return baos.toByteArray();
	}

	public static List<ZipAttachmentVO> demoZipAttachmentVoList() throws IOException {
		List<ZipAttachmentVO> mdPostAttachments = new ArrayList<ZipAttachmentVO>();
		byte[] bytes = Files.readAllBytes(Paths.get("/Users/charlie/Pictures/Temp/demo01.png"));
		byte[] bytes2 = Files.readAllBytes(Paths.get("/Users/charlie/Pictures/Temp/demo02.png"));
		ZipAttachmentVO m1 = new ZipAttachmentVO();
		m1.setAttachment(bytes);
		m1.setFileName("demo01.png");
		ZipAttachmentVO m2 = new ZipAttachmentVO();
		m2.setAttachment(bytes2);
		m2.setFileName("demo02.png");
		mdPostAttachments.add(m1);
		mdPostAttachments.add(m2);
		return mdPostAttachments;
	}

	public static void zipMultipleFiles() throws IOException {
		File newFile = new File("demo.zip");
		List<File> demoByteToFileList = demoFileList();
		FileOutputStream fos = new FileOutputStream(newFile);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		for (File demoFile : demoByteToFileList) {
			FileInputStream fis = new FileInputStream(demoFile);
			ZipEntry zipEntry = new ZipEntry(demoFile.getName());
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
		}
		zipOut.close();
		fos.close();
	}

	public static List<File> demoFileList() throws IOException {
		// demo01
		List<File> files = new ArrayList<File>();
		byte[] bytes = Files.readAllBytes(Paths.get("/Users/charlie/Pictures/Temp/demo01.png"));
		File tempFile = new File("demo01.png");
		FileUtils.writeByteArrayToFile(tempFile, bytes);
		System.out.println(tempFile.getAbsolutePath());

		// demo02
		byte[] bytes2 = Files.readAllBytes(Paths.get("/Users/charlie/Pictures/Temp/demo02.png"));
		File tempFile2 = new File("demo02.png");
		FileUtils.writeByteArrayToFile(tempFile2, bytes2);
		System.out.println(tempFile2.getAbsolutePath());

		files.add(tempFile);
		files.add(tempFile2);

		return files;
	}
}

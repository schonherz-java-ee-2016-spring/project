package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "fileUploadView")
@ViewScoped
public class MBFileUploadView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;

	private UploadedFile uploadedFile;
	private InputStream inputStream;
	private File destFile;
	private File dir;
	private String[] list;
	private String pdfPath;
	String baseDir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("basedir");
	String avatarFileName = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("avatarfilename");
	String documentFileName = FacesContext.getCurrentInstance().getExternalContext()
			.getInitParameter("documentfilename");
	String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	UserVo loggedInUser = new UserVo();

	@PostConstruct
	public void init() {
		try {
			loggedInUser = userService.findUserByName(username);
			dir = new File(baseDir + loggedInUser.getId().toString() + "\\");
			updateDir();
			initPdfPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initPdfPath() {
		this.pdfPath = baseDir + loggedInUser.getId().toString() + "\\" + documentFileName;
		Path pdfPath = Paths.get(this.pdfPath);
		try {
			Files.readAllBytes(pdfPath);
		} catch (Exception e) {
			this.pdfPath = null;
		}
		System.out.println("pdfPath: " + pdfPath);
	}

	private void updateDir() {
		list = dir.list();
	}

	public void actionAvatar(FileUploadEvent event) throws IOException, Exception {
		uploadedFile = (UploadedFile) event.getFile();
		loggedInUser = userService.findUserByName(username);
		inputStream = uploadedFile.getInputstream();
		destFile = new File(baseDir + loggedInUser.getId().toString() + "\\" + avatarFileName);
		FileUtils.copyInputStreamToFile(inputStream, destFile);
		updateDir();
	}

	public void actionDocument(FileUploadEvent event) throws IOException, Exception {
		uploadedFile = (UploadedFile) event.getFile();
		loggedInUser = userService.findUserByName(username);
		inputStream = uploadedFile.getInputstream();
		destFile = new File(baseDir + loggedInUser.getId().toString() + "\\" + documentFileName);
		FileUtils.copyInputStreamToFile(inputStream, destFile);
		updateDir();
		initPdfPath();
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile
	 *            the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the destFile
	 */
	public File getDestFile() {
		return destFile;
	}

	/**
	 * @param destFile
	 *            the destFile to set
	 */
	public void setDestFile(File destFile) {
		this.destFile = destFile;
	}

	/**
	 * @return the dir
	 */
	public File getDir() {
		return dir;
	}

	/**
	 * @param dir
	 *            the dir to set
	 */
	public void setDir(File dir) {
		this.dir = dir;
	}

	/**
	 * @return the list
	 */
	public String[] getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(String[] list) {
		this.list = list;
	}

	/**
	 * @return the pdfPath
	 */
	public String getPdfPath() {
		return pdfPath;
	}

	/**
	 * @param pdfPath
	 *            the pdfPath to set
	 */
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
}

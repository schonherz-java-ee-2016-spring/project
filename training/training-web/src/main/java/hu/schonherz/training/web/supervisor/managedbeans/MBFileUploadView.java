package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

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
	String baseDir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("basedir");
	String avatarFileName = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("avatarfilename");
	String documentFileName = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("documentfilename");
	String pathSeparator = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("pathseparator");

	@PostConstruct
	public void init() {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		UserVo loggedInUser = new UserVo();
		try {
			loggedInUser = userService.findUserByName(username);
			dir = new File(baseDir + loggedInUser.getId().toString() + pathSeparator);
			list = dir.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionAvatar(FileUploadEvent event) throws IOException, Exception {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		UserVo loggedInUser = new UserVo();
		uploadedFile = (UploadedFile) event.getFile();
		loggedInUser = userService.findUserByName(username);
		inputStream = uploadedFile.getInputstream();
		destFile = new File(baseDir + loggedInUser.getId().toString() + pathSeparator + avatarFileName);
		FileUtils.copyInputStreamToFile(inputStream, destFile);
	}

	public void actionDocument(FileUploadEvent event) throws IOException, Exception {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		UserVo loggedInUser = new UserVo();
		uploadedFile = (UploadedFile) event.getFile();
		loggedInUser = userService.findUserByName(username);
		inputStream = uploadedFile.getInputstream();
		destFile = new File(baseDir + loggedInUser.getId().toString() + pathSeparator + documentFileName);
		FileUtils.copyInputStreamToFile(inputStream, destFile);
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

}

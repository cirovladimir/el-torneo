package mx.com.apestudio.gwt.eltorneo.server.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {
	
	final Logger logger=LoggerFactory.getLogger(FileUploadServlet.class);
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // check that we have a file upload request
        if (ServletFileUpload.isMultipartContent(request)) {
            processFiles(request, response);
        }
    }

    private File tmpDir;
    private static final String DESTINATION_DIR_PATH = "/storage/videos";
    private File destinationDir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        tmpDir = new File(((File) getServletContext().getAttribute("javax.servlet.context.tempdir")).toString());

        if (!tmpDir.isDirectory()) {
          throw new ServletException(tmpDir.toString() + " is not a directory");
        }

        logger.debug("tmpDir: " + tmpDir.toString());

        String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(realPath);

        if (!destinationDir.isDirectory()) {
          throw new ServletException(DESTINATION_DIR_PATH + " is not a directory");
        }
    }

    private void processFiles(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // set the size threshold, above which content will be stored on disk
        factory.setSizeThreshold(1 * 1024 * 1024); // 1 MB

        // set the temporary directory (this is where files that exceed the threshold will be stored)
        factory.setRepository(tmpDir);

        // create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // parse the request
            List<?> items = upload.parseRequest(request);
            logger.info("uploading " + items.size() + " files");

            // process the uploaded items
            Iterator<?> itr = items.iterator();

            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();

                // write the uploaded file to the application's file staging area
                File file = new File(destinationDir, item.getName());
                item.write(file);
                logger.info(file.getAbsolutePath()+" written");
            }

        } catch (FileUploadException e) {
            logger.error("Error encountered while parsing the request", e);
        } catch (Exception e) {
            logger.error("Error encountered while uploading file", e);
        }
    }
}
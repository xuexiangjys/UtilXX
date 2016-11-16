package com.xuexiang.util.net.uploadfile;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

import com.xuexiang.util.net.uploadfile.HttpClientUtil.ProgressListener;


/**
 * 甯﹁繘搴︾殑鏂囦欢涓婁紶琛ㄥ崟姝ｆ枃<br>
 * @author MSD_灏忚阿
 *
 */
public class CustomMultipartEntity extends MultipartEntity {	
	private ProgressListener listener;	
	
	public CustomMultipartEntity(HttpMultipartMode browserCompatible,
			String object, Charset forName) {
		super(browserCompatible,object,forName);
	}


	public void setProgressListener(ProgressListener listener){
		this.listener = listener;
	}
	

	@Override
	public void writeTo(OutputStream outstream) throws IOException {
		super.writeTo(new CountingOutputStream(outstream));
	}	
	
	private class CountingOutputStream extends FilterOutputStream {
		private long transferred;
		
		public CountingOutputStream(OutputStream out) {
			super(out);
			this.transferred = 0;			
		}		
		
		@Override
		public void write(int b) throws IOException {
			out.write(b);
			this.transferred++;
			if(listener != null){
				listener.cumulative(this.transferred);			
				listener.progress((int) ((transferred / (float) getContentLength()) * 100));
			}
		}		
		
		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			out.write(b, off, len);
			this.transferred += len;
			if(listener != null){
				listener.cumulative(this.transferred);
				listener.progress((int) ((transferred / (float) getContentLength()) * 100));
			}
		}
	}	
}

package nds.mpm.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

public class ImageUtil 
{
	public static final String DEFAULT_IMG_EXT = "png";
	
	private static final int IMG_WIDTH = 82;
	private static final int IMG_HEIGHT = 130;
	
	/**
	 * 이미지 파일의 mine 타입을 얻는다.
	 * 
	 * @param imgPath
	 * @return
	 */
	public static String imageType(String imgPath)
	{
		String[]
				type = new MimetypesFileTypeMap().getContentType( new File( imgPath ) ).split("/");
		
		if( type[0].equalsIgnoreCase("image") == false )
		{
			return null;
		}

		return type[1];
	}

	/**
	 * 이미지 파일을 디코딩하여 bmp 버퍼로 받는다.
	 * 
	 * @param imgPath
	 * @return	bmp 퍼버
	 * @throws IOException
	 */
	public static BufferedImage read(String imgPath) throws IOException 
	{
		return ImageIO.read(new File(imgPath));
	}

	/**
	 * 메모리에 로드된 이미지를 디코딩하여 bmp 버퍼로 받는다.
	 * @param bin
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage read(byte[] imgbin) throws IOException 
	{
		
		return ImageIO.read(new java.io.ByteArrayInputStream(imgbin));
	}
	
	/**
	 * 메모리에 로드된 이미지를 디코딩하여 bmp 버퍼로 받는다.
	 * @param imgbin
	 * @return
	 */
	public static byte[] resizeImage(byte[] imgbin, String imgExt) throws Exception
	{
		
		BufferedImage bmpBuf = read(imgbin);
		return resizeImage( bmpBuf, IMG_WIDTH, IMG_HEIGHT, imgExt );
	}
	
	/**
	 * 제공된 비트맵 이미지의 크기를 조정하고, 지정된 이미지 포멧으로 변환한다.
	 * @param img
	 * @return
	 */
	public static byte[] resizeImage(BufferedImage bmpBuf, String imgExt) throws Exception
	{
		return resizeImage(bmpBuf, IMG_WIDTH, IMG_HEIGHT, imgExt);
	}
		
	/**
	 * 버퍼에 담겨있는 이미지를 지정된 사이즈로 조정하고, 지정된 이미지 타임으로 인코딩된 이미지 버퍼를 받는다.
	 * 
	 * @param bmpBuf
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] resizeImage(BufferedImage bmpBuf, int width, int height, String imgExt)
	{
		int				pixelType = bmpBuf.getType() == 0 ? BufferedImage.SCALE_SMOOTH : bmpBuf.getType();
		BufferedImage	destBuf = new BufferedImage( width, height, pixelType );
		
		{
			Graphics2D		
			g = destBuf.createGraphics();
			g.drawImage(bmpBuf, 0, 0, width, height, null );
			g.dispose();
		}
		
		return bmpToImage( destBuf, imgExt );
	}
	
	public static byte[] cropImage(BufferedImage bmpBuf, int x, int y, int width, int height, String imgExt)
	{
		int				pixelType = bmpBuf.getType() == 0 ? BufferedImage.SCALE_SMOOTH : bmpBuf.getType();
		BufferedImage	destBuf = new BufferedImage( width, height, pixelType );
		
		{
			Graphics2D		
			g = destBuf.createGraphics();
			g.drawImage(bmpBuf, x, y, width, height, null );
			g.dispose();
		}
		
		return bmpToImage( destBuf, imgExt );
	}
	
	/**
	 * 지정된 크기 이상일 경우 resize 하며 지정된 가로 세로 비율로 맞춰주는 메서드이다. 만약 가로가 길거나 세로가 길면 이미지 가운데를 기준으로 넘치는 부분을 잘라낸다.
	 * 
	 * */
	public static byte[] cropImageOfCenter( byte[] imageContent, int maxWidth, double xyRatio) throws IOException {
		
        BufferedImage originalImg = ImageIO.read( new ByteArrayInputStream(imageContent));
 
        //get the center point for crop
        int[] centerPoint = { originalImg.getWidth() /2, originalImg.getHeight() / 2 };
 
        //calculate crop area
        int cropWidth=originalImg.getWidth();
        int cropHeight=originalImg.getHeight();
 
        if( cropHeight > cropWidth * xyRatio ) {
            //long image
            cropHeight = (int) (cropWidth * xyRatio);
        } else {
            //wide image
            cropWidth = (int) ( (float) cropHeight / xyRatio) ;
        }
 
        //set target image size
        int targetWidth = cropWidth;
        int targetHeight = cropHeight;
 
        if( targetWidth > maxWidth) {
            //too big image
            targetWidth = maxWidth;
            targetHeight = (int) (targetWidth * xyRatio);
        }
 
        //processing image
        BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = targetImage.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, targetWidth, targetHeight);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(originalImg, 0, 0, targetWidth, targetHeight,   centerPoint[0] - (int)(cropWidth /2) , centerPoint[1] - (int)(cropHeight /2), centerPoint[0] + (int)(cropWidth /2), centerPoint[1] + (int)(cropHeight /2), null);
 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(targetImage, "png", output);
 
        return output.toByteArray();
        
	}
	
	/**
	 * 메모리상에 존재하는 비트맵 배열을 지정한 이미지로 변환한다.
	 * 
	 * @param imgBuf	메모리에 로드된 비트맵 버퍼.
	 * @param ext		변환될 이미지 확장자
	 * @return
	 * 					성공하면 bmp 배열. 실패하면 null.
	 */
	public static byte[] bmpToImage(BufferedImage bmpBuf, String imgExt)
	{
		ByteArrayOutputStream	baos = new ByteArrayOutputStream();
		byte[]					bmpData = null;
		
		try 
		{
			ImageIO.write( bmpBuf, imgExt, baos );
			baos.flush();
			bmpData = baos.toByteArray();
			
			baos.close();
		}
		catch(IllegalArgumentException e)
		{
			System.out.println( "[IllegalArgumentException] "+e.getMessage() );
		}
		catch(IOException e) 
		{
			System.out.println( "[IOException] "+e.getMessage() );
		}
		
		return bmpData;
	}
	
	/**
	 * BufferedImage bmpBuf 사용 이미지 처리시 jpg 이미지 중 색상유실되는 현상 발생
	 * 
	 * PixelGrabber 처리방식으로 변경
	 * 
	 * */
	public static byte[] resizeImage(byte[] imgbin, int destWidth, int destHeight, String imgExt) throws Exception
	{
		
		Image srcImg = ImageIO.read(new java.io.ByteArrayInputStream(imgbin));
         
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
        int pixels[] = new int[destWidth * destHeight]; 
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        try {
             pg.grabPixels();
        } catch (InterruptedException e) {
             throw new IOException(e.getMessage());
        } 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.SCALE_SMOOTH); 
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
         
 		return bmpToImage(destImg, imgExt);
	}
	
	/**
	 * 파일 이미지를 읽고 지정된 사이즈, 확장자 타입으로 리사이징한다.
	 * 
	 * @param bmpBuf
	 * @param width
	 * @param height
	 * @return
	 * 
	 * 2016-03-06
	 * 이미지 리사이즈하는 처리로 사용중 아래와 같은 버그로 BufferedImage 인수로 처리되는 메소드로 교체.
	 * 이미지 처리시 아래와 같은 오류를 갖고 있음.
	 * png파일 bg투명시 background 처리를 blank으로 처리되는 오류,
	 */
	public static byte[] resizeImage(String imgPath, int destWidth, int destHeight, String imgExt) throws Exception
	{
		Image srcImg =  ImageIO.read(new File(imgPath));
        
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
        int pixels[] = new int[destWidth * destHeight]; 
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        try {
             pg.grabPixels();
        } catch (InterruptedException e) {
             throw new IOException(e.getMessage());
        } 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.SCALE_SMOOTH); 
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
         
 		return bmpToImage(destImg, imgExt);
	}
}

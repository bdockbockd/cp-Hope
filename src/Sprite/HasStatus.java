package Sprite;

import java.util.List;

import Constant.Images;
import javafx.scene.image.Image;

public interface HasStatus {

	int DEFAULT_STATUS = 0;

	public int getStatus();

	public void setStatus();

	void checkStatus();

}

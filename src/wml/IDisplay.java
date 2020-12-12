package wml;
enum colorType{
	RED, BLUE, PURPLE, BLACK,
	GREEN, TRANS, 
	B_RED, B_BLUE,
	B_PURPLE, B_BLACK, B_GREEN, B_TRANS
};

public interface IDisplay {
	void outputColor(String s, colorType color, colorType back, boolean isMid);
	void showStatusBar(double percent, colorType color, colorType back);
}

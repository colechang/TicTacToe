
public class nk_TicTacToe {
	char[][] gameBoard;
	private int inline;
	private int max_levels;
	private int board_size;
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		this.inline = inline;
		this.max_levels = max_levels;
		this.board_size = board_size;	
		gameBoard = new char[board_size][board_size];
		
		for(int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
			}
		}
	}
	public Dictionary createDictionary() {
		int dictsize = 7001;
		Dictionary dict = new Dictionary(dictsize);
		return dict;
	}
	public int repeatedConfig(Dictionary configurations) {
		String config ="";
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				config = config + gameBoard[i][j];
			}
		}
		if(configurations.get(config) != -1) {
			return configurations.get(config);
		}
		else
			return -1;
		}
	public void insertConfig(Dictionary configurations, int score) {
		String config = "";
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				config = config + gameBoard[i][j];
			}
		}
		Record record = new Record(config,score);
	try {
		configurations.insert(record);
	}
	catch(DictionaryException e) {
		System.out.println(e);
	}
	}
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	public boolean squareIsEmpty(int row, int col) {
		if(gameBoard[row][col] == ' ') {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean wins(char symbol) {
		if(horizontal(symbol) || vertical(symbol) || diagonalLR(symbol) || diagonalRL(symbol)){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isDraw() {
		boolean indicator = false;
		
		for(int i = 0; i<board_size; i++) {
			for(int j = 0; j< board_size; j++) {
				if(squareIsEmpty(i,j)) {
					indicator = true;
				}
			}
		}
		boolean x;
		boolean o;
		x = wins('x');
		o = wins('o');
		return(!indicator && !x && !o);
	}
	public int evalBoard() {
		if(wins('o')) {
			return 3;
		}
		else if(wins('x')) {
			return 0;
		}
		else if(isDraw() == true) {
			return 2;
		}
		else {
			return 1;
		}
	}
	private boolean horizontal(char symbol) {
		int count = 0;
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				if(gameBoard[i][j] == symbol) {
					count = count + 1;
				}
				else {
					count = 0;
				}
			}
			if(count >= inline){
				return true;
			}
		}
		return false;
	}
	private boolean vertical(char symbol) {
		int count = 0;
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				if(gameBoard[i][j] == symbol) {
					count = count + 1;
				}
				else {
					count = 0;
				}
			}
			if(count >= inline){
				return true;
			}
		}
		return false;
	}
	private boolean diagonalLR(char symbol) {
		int count = 0;
		for(int i = 0; i < board_size; i++) {
			if(gameBoard[i][i] == symbol) {
				count = count + 1;
			}
			else {
				count = 0;
			}
		}
		if(count >= inline) {
			return true;
		}
		else {
			return false;
		}
	}
	private boolean diagonalRL(char symbol) {
		int count = 0;
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				if(gameBoard[i][j] == symbol && i + j ==(inline -1)) {
					count = count + 1;
				}
			}
		}
		if(count >= inline) {
			return true;
		}
		else {
			return false;
		}
	}
}

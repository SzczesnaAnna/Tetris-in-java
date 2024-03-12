package tetrisPackage;

public record Tutorial()
{
	static String PLtutorial = "Gra rozpoczyna się na prostokątnej planszy (początkowo pustej) zwanej tetrionem lub matriksem,"
		+System.lineSeparator()+"ułożonej krótszym bokiem w poziomie. Tetrion ma wymiary 20 wierszy na 10 kolumn. W trakcie gry,"
		+System.lineSeparator()+"pośrodku górnej krawędzi planszy, pojawiają się pojedynczo klocki złożone z czterech małych"
		+System.lineSeparator()+"kwadratów nazywanych też blokami. Klocki te (określane mianem „tetrimino”[3]) przemieszczają się"
		+System.lineSeparator()+"w kierunku dolnej krawędzi w miarę możliwości. Kiedy jedno tetrimino opadnie na samo dno, zostaje"
		+System.lineSeparator()+"unieruchomione, a następne ukazuje się u góry planszy. Gra trwa aż do momentu, w którym klocek"
		+System.lineSeparator()+"nie będzie mógł pojawić się na planszy. Zadaniem gracza jest układanie tetrimino na planszy"
		+System.lineSeparator()+"(poprzez wykorzystanie rotacji i przesuwanie klocków w poziomie) w taki sposób, aby kwadraty"
		+System.lineSeparator()+"składające się na nie utworzyły wiersz na całej szerokości prostokąta. W takiej sytuacji wiersz"
		+System.lineSeparator()+"ten zostaje usunięty, a pozostałe klocki opadają w kierunku dna, tworząc więcej przestrzeni dla"
		+System.lineSeparator()+"następnych elementów. Po usunięciu określonej liczby wierszy prędkość gry wzrasta o pół sekundy,"
		+System.lineSeparator()+"co utrudnia tym samym precyzyjne sterowanie kolejnymi tetrimino.";
		
	static String ENGtutorial = "Game begins on rectangular board (empty at first) called tetrion or matrix,"
		+System.lineSeparator()+"laid with its shorter edge horizontally. Tetrion is composed of 20 rows and 10 columns. During the game,"
		+System.lineSeparator()+"in the middle of the boards upper edge, appear one by one figures made of four small"
		+System.lineSeparator()+"squares also called blocks. Those figures (called „tetrimino”[3]) move"
		+System.lineSeparator()+"in the direction of lower edge if possible. When one tetrimino falls to the bottom, it becomes"
		+System.lineSeparator()+"immobile, while the next one shows itself at the top of the board. The game lasts till the moment, when the next tetromino"
		+System.lineSeparator()+"won't be able to appear on the tetrion. The players task is to arrange tetrimino on the board"
		+System.lineSeparator()+"(by rotating and moving them horizontally) in such a way, that the blocks"
		+System.lineSeparator()+"that make them up align in a board-wide row. At that moment that row"
		+System.lineSeparator()+"is then removed, and the blocks on top of it fall down, creating more space for"
		+System.lineSeparator()+"next elements. After removing a certain number of rows, the game speeds up,"
		+System.lineSeparator()+"which makes precise steering of next tetrimino more difficult.";
}
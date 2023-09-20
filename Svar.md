# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

   
## Oppgave 1
i)
 Noen viktige klasser er:
Superklassen Game, som modellerer turn-based spill. Connect-four, Tic Tac Toe og Othello er turn based spill og dermed er Game klassen viktig.
PlayerList klassen er også viktig for den holder styr på hvor mange spillere det er, og hvem sin tur det er, som da selvfølgelig er viktig i turn-based spill.
AbstractPlayer klassen er også enormt viktig, den er selve fundamentet for Player klassen og skiller spillerene fra hverandre.

ii)
Abstraksjon er brukt i AbstractPlayer, og som det står i kommentarene så slipper man å skrive inn koden flere ganger i alle Player klassene, da AbstractPlayer gjør det en gang for alle.

Encapsulation er når andre klasser må gå gjennom en public for å hente ut data fra en private en. Eksempel på dette er i GameBoard i public int countNumInRow og da private int countNumInRow. 

Inheritance er brukt i ClickableGrid klassen. ClickableGrid klassen er parent-klassen mens ClickableGridListener er child-klassen som inheriter fra parent-klassen. ClickableGridListener extender MouseAdapter.

Polymorphism er når det er flere klasser som er i relasjon til hverandre. De bruker det forrige prinsippet med inheritance med at de extender andre klasser med mer informasjon og er tilkynttet hverandre. Det vil jeg si skjer med Player. MiniMaxPlayer, ConsolePlayer, GuiPlayer, RandomPlayer og DumbPlayer extender AbstractPlayer. Så det er tydelig at prinsippet inheritance er brukt flere ganger dermed polymorphism. AbstractPlayer fungerer som en Superclass eller parent-klass mens alle som extender den fungerer som child-klass.


iii))
Jeg tror et spill som for eksempel kinasjakk ville vært enkelt å legge til. Kinasjakk er også et turn-based spill der man gjør trekk etter hverandre. I kinasjakk så kan man bevege brikker ganske langt og det er et brett med masse felt også. Man vinner også når man har fått alle brikkene sine til andre siden, så da vil den ene spilleren vinne når alle brikkene har kommet frem. Man får en utfordring her, og det er at gridet til kinasjakk ikke er firkantet som det er her.

Det ville vært naturlig å mene at sjakk også er lett å legge til, men i sjakk er det litt mer komplisert enn i kinasjakk, fordi brikkene i sjakk har jo en funksjon og det er ikke alle brikkene som kan ta andre brikker.

Checkers er også lett å legge til.


iv))
SOLID prinsipper:

1) Single Responsibility principle i Location klassen. Location skal bare holde styr på row, column koordinater og metoder.

2) Open / Closed principle. GameBoard setter bare spilleren til en spesifikk posisjon. Dersom den er null så lar den ikke Othello erstatte O med X. Når Othello da er implementert har GameBoard en metode til, put, som setter spilleren på boardet. Det går jo ikke, fordi Open Closed principle skal være åpen for utvidelser, men ikke modifiseringer.

3) Interface Segregation principle. Interfacet skal splittes i flere mindre interfaces. IGrid interfacet forteller hvilket methods som er for Grid, det samme gjør Player interfacet også, men for Player. Dette oppfyller Interface Segregation principle. Hvis det hadde vært et PlayerGrid interface med alle metodene så hadde det ikke vært oppfylt.

4) Dependency Inversion principle. Prosjektet har tre spill, Connect four, TicTacToe og Othello. Hvert spill er implementert i sin egen klasse. Fellesnevneren her ligger i superklassen Game. Derfor oppfylles dette prinsippet også.


## Oppgave 2
Plan:

Othello er også et turn-based spill, så mye av funksjonaliteten trenger ikke å endres for å implementere Othello. For eksempel Game.java som jeg allerede har sagt at den holder styr på turn-based games. Det samme gjelder gridet til Connect-four. Det er 7x6, mens Othello skal være 8x8. Dermed kan den griden brukes etter den har blitt utvidet litt.

Connect-four sine spillebrikker er rød og blå, så det vil nok også være greit å bruke den koden men bare endre fargene til svart og hvit. Brikkene er også runde, som også er perfekt for Othello.

Dermed må jeg lage en Othello klasse, og fjerne gravity-drop funksjonen i Connect four, og da kode på nytt metoder for å plassere brikker, samt for at de skal bytte farge, og for at den ene spilleren vinner hvis den har flertallet av de 64 brikkene når spillet er over. Og at hvis når spillet er over og det er like mange brikker til hver så blir det uavgjort.

Klassediagram er fikset. Dette finnes i information, jeg fjernet pdf filen og la til klasse diagrammet der.


## Oppgave 3

Tester ligger i OthelloGameTest.java.


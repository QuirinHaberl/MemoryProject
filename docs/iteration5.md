# SE Team 19 - Iteration 5

## Team

**Team (mit Github Name):**
- Isabella Habereder (IsaHab)
- Daqian Zhang (Zhang-Daqian)
- Jan Pfeifer (pfeifer-j)
- Quirin Haberl (QuirinHaberl)
- Florian Muhrer (FlorianMuhrer)


**Scrum Master für Iteration 5:**
1. Florian Muhrer
2. Quirin Haberl
3. Isabella Habereder
4. Jan Pfeifer
> 5. Daqian Zhang


**Organisation:**
- Mehrmalige, wöchentliche Absprache über Zoom und WhatsApp.
- Fortschritte, Probleme und Fragen wurden in der Gruppe besprochen.
- Die Presentation von 15.07.2021 vorbereiten.

**Agenda für Iteration 5:**
- GUI:
    - das Spielergebnis in einem separaten Fenster erscheinen wurde durch Quirin und Florian realisiert.
    - Sounds wurden GUI durch Jan hinzugefuegt.
    - Implementierung des Einzelspielers in der GUI durch Quirin und Florian.
    - Achievement wird angezeigt wenn game beendet.
    - Dynamische Text Anpassung bei Achievement wurde durch Florian realisiert.
    - Ein eigenes des Fensters wird In jedem Fenster der Stage geladen.
    - Spielmenü(Startmenü,neustart des Spiels,Spiel beenden) während dem Spiel kann man aufrufen.
    - SinglePlayerModus in der GUI durch Quirin und Florian hinzugefuegt 
    - das Spielergebnis in einem separaten Fenster erscheinen wurde durch Quirin und Florian realisiert.
    - Klicken auf die jeweiligen Kartenbuttons und der Timer im Einzelmodus wurden durch Isabella implementiert.
    - Die Spielerfrofile im GUI wurde durch Jan erweitert.
    - Die Achievements auf der Konsole und PlayerLabels mit Score im Spiel angezeigt wurde durch Jan realisiert.
- Refactoring:
    -Der geschriebene Code aller Klassen des Projekts wurden durch Team gerefactored.
- JUnit Tests:
    - Die Tests von Player und PlayerList wurden durch Daqian geschrieben.
    - Die Tests von PlayerlingField und Card wurden durch Daqian geschrieben.
    - Die Tests von Game und DataDisplay wurden  durch Isabella geschrieben.
    - Die Tests von main, view und GUi wurden durch Florian geschrieben.
    - Die Tests von DataBase, HighScoreHistory, Profile und Achievement wurden von Jan geschrieben.
- Der gesamte Code ist mit Java-Docs versehen.  
- Code:
    - Zurücksetzung der inGame Achievements nach jedem Match
    - Korrigiere die Speicherung der Profile
    - Update der shuffle()-Methode durch Jan

**Anhang:**  
Protokolle der Zoom-Meetings:
- Protokoll_07_07_21.txt
- Protokoll_11_07_21.txt
- Protokoll_18_07_21.txt

**Klassendiagramm:**  
Das Klassendiagramm wurde im Team aktualisiert und von Quirin generiert. 

**Iteration 5**

#User Stories

**E = Erstellt**
**B = Beigetragen**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #17 |E|||B|
| #33 ||B|E|B||
| #180 |||E|||
| #20 |E|||||


#Tasks

**P = Planung**
**R = Realisierung**
**C = Closed**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #166 |R|R|P,R|R|R|
| #175 ||R|P|||
| #177 ||R|P|||
| #219 |R|R|R|P,R|R|
| #259 |||P,R|||
| #168 |||P|||
| #260 |||P,R|||
| #220 ||||P,R||
| #202 |||P, R|||
| #176 |R||P|||
| #226 |||P, R|||
| #178 |||P||R|
| #238 |||||P,R|
| #236 |||||P,R|
| #237 |||||P,R|
| #205 |||P, R|||
| #201 |||P,R|||
| #199 |||P,R|||
| #184 |||||P,R|
| #198 |||P,R|||
| #218 ||||P,R|R|
| #216 ||||P,R|R|
| #187 |||P,R|||
| #212 |P,R|||||
| #213 |P, R|||||
| #196 |||P,R|||
| #195 |||P,R|||
| #194 |||P|||
| #186 |||P,R|||
| #179 |||P,R|||
| #197 |||P,R|||
| #190 |||P,R|||
| #191 |||P,R|||

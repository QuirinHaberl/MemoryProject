package Model;

    /**
     * this class implements the Player of the game.
     */
    public class Player {
        /**
         * the attribute name is the name of a Player
         */
        String name;
        /**
         * the attribute score is the score that a Player got in the game
         */
        int score;
        /**
         * the attribute Player next is used for connectting to the next Player
         */
        Player next;

        /**
         * the attribute Player rear is used for connectting to the rear Player
         */
        Player rear;

        /**
         * constructs a new Player
         * @param name
         * @param score
         * @param next
         */
        public Player(String name, int score, Player next, Player rear){
            this.name = name;
            this.score = score;
            this.next = next;
            this.rear = rear;
        }

        /**
         * set a name for Player
         * @param name of a Player
         */
        public void setName(String name){
            this.name = name;
        }

        /**
         * @return the name of a {@link Player}
         */
        public String getName(){
            return name;
        }

        /**
         * set the score for a Player
         * @param score of a Player
         */
        public void setScore(int score){
            this.score = score;
        }

        /**
         * if a Player found two same card, then the score of the Player will added 1.
         */
        public void addScore(){ // +1
            this.score ++;
        }

        /**
         *
         * @return the score of a {@link Player}
         */
        public int getScore(){
            return score;
        }

        /**
         * sets the next Player
         * @param next of the {@link Player}
         */
        public void setNext(Player next){
            this.next = next;
        }

        /**
         *
         * @return the next {@link Player}
         */
        public Player getNext(){
            return next;
        }

        /**
         *
         * @return the rear {@link Player}
         */
        public Player getRear() {
            return rear;
        }

        /**
         * sets the rear Player
         * @param rear of the {@link Player}
         */
        public void setRear(Player rear) {
            this.rear = rear;
        }

        /**
         * outputs the name and the score of a Player on the console
         */
        public void outputStatusOfPlayer(){
            System.out.println("name:" + this.name + ", and the score: " + this.score + "." );
        }

    }

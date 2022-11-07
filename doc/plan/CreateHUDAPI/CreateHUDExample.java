//ROUGH example of how the API methods could be used to save a game
//does not compile

public HUDMaker implements CreateHUD{
public HUDMaker(Model m, View v){
            model  = m
            view = v
        }
        //implement all the interface methods here

        }

        InfoPair time = createTimeView();

        InfoPair score = createScoreView();

        ImageGroup powerups = createPowerupsView();

        HealthView health = createHealthView();

        HUD hud = packageHUD();

        //within view class
        public void step(){
            time+=1;
            updateTime();
        }

        //within player class
        public void onHit(){
            health -= 1
            updateHealthView();
        }

        public void onPowerupCollection(){
            img = Powerup.getIcon();
            updatePowerupView(img);
        }
        //within enemy class
        public void onDeath(){
            updateScore();
            upDateScoreView();
        }
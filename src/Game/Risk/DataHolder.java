package Game.Risk;

import Game.Model.*;

import java.io.File;
import java.util.*;

/**
 * A singleton class to hold the entire data set throughout the application.
 * 
 * @author Jay
 * @version 1.0.0
 */
public class DataHolder {
    public static final int REINFORCEMENT_PHASE = 0;
    public static final int ATTACK_PHASE = 1;
    public static final int FORTIFICATION_PHASE = 2;

    /** a holder that manipulates the phases */
    private PhaseData phaseData = new PhaseData();
    public int currentPhase = -1;
    public int playerTurn = 0;
    /** instance of the singleton class */
    private static DataHolder dataHolder;

    /** List of continents on the map */
    private List<ContinentData> continentDataList = new ArrayList<>();
    /** List of countries on the map */
    private List<CountryData> countryDataList = new ArrayList<>();
    /** List of player in the game */
    private HashMap<String, Player> playerList = new HashMap<>();
    /** List of number of armies a player has */
    private HashMap<Integer,Integer> playersArmiesList=new HashMap<>();
    /** list of players conquered in the game **/
    private List<Player> conqueredPlayerList = new  ArrayList<>();
    /** Meta data of the map */
    public MapData mapData = new MapData();
    /** Image file of the map */
    public File bmpFile;
    /** Is the armies distribution be automatic or manual? */
    public boolean isArmiesAutomatic = false;

    /** Returns the active phase */
    public int getCurrentPhase() {
        return this.phaseData.getCurrentPhase();
    }

    /**
     * Attaches the observer to the phase data to detect changes in phases
     * @param object the observer to attach
     */
    public void attachObserverToPhase(Observer object) {
        this.phaseData.deleteObserver(object);
        this.phaseData.addObserver(object);
    }

    /**
     * Attaches the observer to all the players
     * @param object the observer to attach
     */
    public void attachObserverToPlayers(Observer object) {
        for (Map.Entry<String, Player> entry : this.playerList.entrySet()) {
            Player player = entry.getValue();
            player.deleteObserver(object);
            player.addObserver(object);
        }
    }

    /**
     * Get the instance of the singleton class.
     * It first checks if there is an existing instance.
     * If not, then it creates a new one.
     * @return the instance of the singleton object.
     */
    public static DataHolder getInstance() {
        if (dataHolder == null)
            dataHolder = new DataHolder();
        return dataHolder;
    }

    public List<Player> getConqueredPlayerList() {
        return conqueredPlayerList;
    }

    public void setConqueredPlayerList(List<Player> conqueredPlayerList) {
        this.conqueredPlayerList = conqueredPlayerList;
    }

    /** Deletes all the players from the game play */
    public void clearPlayers() {
        this.playerList.clear();
    }

    /**
     * Add a player into the game play
     * @param data data object of the player
     */
    public void addPlayer(Player data) {
        this.playerList.put(data.getName(), data);
        this.phaseData.setTotalPlayers(this.playerList.size());
    }

    /**
     * Add a country into the game play
     * @param data data object of the player
     */
    public void addContinent(ContinentData data) {
        this.continentDataList.add(data);
    }

    /**
     * Add a country into the game play
     * @param data data object of the player
     */
    public void addCountry(CountryData data) {
        this.countryDataList.add(data);
    }

    public List<ContinentData> getContinentDataList() {
        return continentDataList;
    }

    public List<CountryData> getCountryDataList() {
        return countryDataList;
    }

    /**
     * Get the country data object of a particular country
     * @param name name of the country
     * @return data object of the country
     */
    public CountryData getCountry(String name) {
        for (CountryData countryData : this.countryDataList) {
            if (countryData.getName().equalsIgnoreCase(name))
                return countryData;
        }

        return null;
    }

    public List<Player> getPlayerList() {
        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, Player> playerEntry : this.playerList.entrySet()) {
            players.add(playerEntry.getValue());
        }

        return players;
    }

    public HashMap<Integer, Integer> getPlayersArmiesList() {
        return playersArmiesList;
    }

    public void setPlayersArmiesList(int playerId, int noOfArmies) {
        if(playersArmiesList.keySet().contains(playerId)) {
            playersArmiesList.replace(playerId, noOfArmies);
        }
        else {
            playersArmiesList.put(playerId, noOfArmies);
        }
    }

    /**
     * Update the list of players in the game.
     * @param players list of players to update to.
     */
    public void updatePlayerList(List<Player> players) {
        for (Player player : players) {
            this.playerList.put(player.getName(), player);
        }
    }

    /**
     * get the list of countries in the continent
     * @param continentName name of the continent
     * @return data object of the continent
     */
    public List<CountryData> countCountriesInContinent(String continentName) {
        List<CountryData> countryDataList = new ArrayList<>();

        for (CountryData country : this.countryDataList) {
            if (country.getContinent().equalsIgnoreCase(continentName))
                countryDataList.add(country);
        }

        return countryDataList;
    }

    /** Changes the player for the turn */
    public void changeTurn() {
        this.phaseData.changeTurn();
    }

    /**
     * Get the player object from his name
     * @param name name of the player
     * @return data object of the player
     */
    public Player getPlayer(String name) {
        return this.playerList.get(name);
    }

    /** changes the phases in each turn. and it automatically changes the player turn */
    public void changePhases() {
        this.phaseData.changePhase();
    }

    /** 
     * get the active player on the board 
     * 
     * @return activePlayer The active player for the game.
     */
    public Player getActivePlayer() {
        return this.getPlayerList().get(this.phaseData.getPlayerTurn());
    }

    /**
     * Update the active player object.
     * It's used in order to update the conquered countries and its armeis.
     * @param player data object of the player
     */
    public void updatePlayer(Player player) {
        this.playerList.put(player.getName(), player);
    }
}

package com.yue.ssl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ShaShaConfession {

    private static ShaShaConfession ssc = null;

    private String strTal = null;
    private String strBirthday = null;
    private int money = 0;

    /**
     * reader(System.in).
     */
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ShaShaConfession() {

        try {
            strTal = getTal();
            strBirthday = getBirthday();
            money = getMoney();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        strTal = format(strTal);
        strBirthday = format(strBirthday);

        Optional<String> optionalTal = Optional.ofNullable(strTal);
        Optional<String> optionalBirthday = Optional.ofNullable(strBirthday);

        strTal = optionalTal.orElse("Not completed");
        strBirthday = optionalBirthday.orElse("Not completed");
    }

    /**
     * Get Instance.
     * @return ssc
     */
    public static ShaShaConfession getInstance() {
        if(ssc == null) {
            ssc = new ShaShaConfession();
            return ssc;
        }
        return ssc;
    }

    /**
     * Get Talking Result.
     * @return Console read
     * @throws IOException IOException
     */
    private @NotNull String getTal() throws IOException {
        System.out.println("Talking done? if done, enter done");
        return reader.readLine().toLowerCase();
    }

    /**
     * Get Birthday Result.
     * @return Console read
     * @throws IOException IOException
     */
    private @NotNull String getBirthday() throws IOException {
        System.out.println("Get her birthday? if get, enter done");
        return reader.readLine().toLowerCase();
    }

    /**
     * Get Money Result.
     * @return Console read
     * @throws IOException IOException
     */
    private int getMoney() throws IOException {
        System.out.println("Please enter how much money you have?");
        return Integer.parseInt(reader.readLine());
    }

    /**
     * if the String is empty, make it to null.
     * @param str String
     * @return null or str
     */
    @Contract(pure = true)
    private @Nullable String format(@NotNull String str) {
        if(str.isEmpty())
            return null;
        return str;
    }

    /**
     * Talking is success or not.
     * @param str String
     * @return str.equals("done")
     */
    @Contract(pure = true)
    private boolean talking(@NotNull String str) {
        return str.equals("done");
    }

    /**
     * BirthDay is success or not.
     * @param str String
     * @return str.equals("done")
     */
    @Contract(pure = true)
    private boolean birthDay(@NotNull String str) {
        return str.equals("done");
    }

    /**
     * ShaSha is success or not.
     * @param money Money
     * @return money >= 599
     */
    private boolean ShaSha(int money) {
        return money >= 599;
    }

    /**
     * Confession is success or not.
     * @param tal {@link #talking(String)}
     * @param birthday {@link #birthDay(String)}
     * @param shasha {@link #ShaSha(int)}
     * @return tal & birthday & shasha
     */
    private boolean confession(boolean tal, boolean birthday, boolean shasha) {
        return tal & birthday & shasha;
    }

    /**
     * get result
     * @param tal Talking result
     * @param birthday Birthday result
     * @param shasha ShaSha result
     * @return string
     */
    private String setResult(boolean tal, boolean birthday, boolean shasha) {
        if (confession(tal, birthday, shasha))
            return "Now, You have a gf.";
        return "Sorry, But you still have not a gf.";
    }

    /**
     * show result
     * @return string
     */
    public String getResult() {
        boolean tal = talking(strTal);
        boolean birthday = birthDay(strBirthday);
        boolean shasha = ShaSha(money);

        return "\n=============================================================\n" +
                "The Process(true or false): " +
                tal + ", " +
                birthday + ", " +
                shasha + "\n" +
                "Your Input(If null, it will be \"Not completed\"):" +
                strTal +
                ", " +
                strBirthday +
                ", " +
                money +
                "\n" +
                "=============================================================\n" +
                "Result: " +
                setResult(tal, birthday, shasha) + "\n";
    }
}

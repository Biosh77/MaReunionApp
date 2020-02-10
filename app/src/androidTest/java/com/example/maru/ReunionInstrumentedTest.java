package com.example.maru;


import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.maru.list_reunion.ListReunionActivity;
import com.example.maru.model.Reunion;
import com.example.maru.utils_test.RecyclerViewUtils;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.maru.utils_test.RecyclerViewUtils.clickChildView;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class ReunionInstrumentedTest {

    private ListReunionActivity mActivity;
    private int currentUsersSize = 0;


    @Rule
    public ActivityTestRule<ListReunionActivity> mActivityRule = new ActivityTestRule(ListReunionActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    @Test
    public void myReunionList_shouldBeEmpty() {

        onView(withId(R.id.activity_list_reunion_rv))
                .check(matches(hasMinimumChildCount(0)));
    }

    @Test
    public void checkIfAddingReunionIsWorking() {
        onView(withId(R.id.add_reunion)).perform(click());//android espresso how to test datePicker
        onView(withId(R.id.hour)).perform(click());
        onView(withText("OK")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.place)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.place)).check(matches(withSpinnerText(containsString("Salle A"))));
        onView(withId(R.id.subject)).perform(clearText(), typeText("Peach"));
        onView(withId(R.id.Date_Reunion)).perform(click());
        onView(withText("OK")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.mail)).perform(scrollTo(), click(), typeText("Meunier.jordane@gmail.com")).perform(pressImeActionButton());
        onView(withId(R.id.Validate)).perform(scrollTo(), click());
        onView(withId(R.id.activity_list_reunion_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize + 1));
    }


    @Test
    public void checkIfRemovingReunionIsWorking() {
        mActivityRule.getActivity().addReunion(new Reunion("16h00", "C", "Peach", Arrays.asList("Meunier.jordane@gmail.com"), "16/12/19"));
        onView(withId(R.id.activity_list_reunion_rv))
                .perform(actionOnItemAtPosition(0, clickChildView(R.id.item_list_user_delete_button)));
        onView(withId(R.id.activity_list_reunion_rv)).check(new RecyclerViewUtils.ItemCount(0));
    }

    @Test
    public void checkIfFilterIsWorking() {
        mActivityRule.getActivity().addReunion(new Reunion("16h00", "Salle C", "Peach", Arrays.asList("Meunier.jordane@gmail.com"), "12/12/19"));
        mActivityRule.getActivity().addReunion(new Reunion("18h00", "Salle B", "Peach", Arrays.asList("Meunier.jordane@gmail.com"), "20/12/19"));
        mActivityRule.getActivity().addReunion(new Reunion("15h00", "Salle K", "Peach", Arrays.asList("Meunier.jordane@gmail.com"), "22/12/19"));
        mActivityRule.getActivity().addReunion(new Reunion("20h00", "Salle A", "Peach", Arrays.asList("Meunier.jordane@gmail.com"), "16/12/19"));
        onView(withId(R.id.filter)).perform(click());
        onView(withId(R.id.place_filter)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is("Salle A")))
                .inRoot(isPlatformPopup())
                .perform(click());
        onView(withId(R.id.place_filter)).check(matches(withSpinnerText(containsString("Salle A"))));
        onView(withId(R.id.date_filter)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 12, 16));
        onView(withText("OK")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withText("VALIDER")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.activity_list_reunion_rv)).check(new RecyclerViewUtils.ItemCount( 1));
    }
}
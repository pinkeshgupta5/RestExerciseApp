package com.poc.pinkesh.androidexercisepoc;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.poc.pinkesh.androidexercisepoc.Fragments.HomeFragment;
import com.poc.pinkesh.androidexercisepoc.ViewHolder.ViewHolderUser;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().
                add(R.id.top_layout, new HomeFragment(),
                        null).commit();
    }

    @Test
    public void shouldAbleToLaunchHomeScreen() {
       // onView(withId(R.id.toolbar_actionbar)).check(matches(isDisplayed()));
       // onView(withId(R.id.ItemListView)).check(matches(isDisplayed()));
        //onView(withId(R.id.ItemListView)).perform(RecyclerViewActions.scrollToPosition(3));
        onView(withId(R.id.ItemListView)).perform(
                RecyclerViewActions.scrollToHolder(
                        withHolderView(mActivityRule.getActivity().getString(R.string.title))
                )
        );
    }

    public static Matcher<RecyclerView.ViewHolder> withHolderView(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, ViewHolderUser>(ViewHolderUser.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(ViewHolderUser item) {
                TextView itemtitle = (TextView) item.itemView.findViewById(R.id.itemtitle);
                if (itemtitle == null) {
                    return false;
                }
                return itemtitle.getText().toString().contains(text);
            }
        };
    }

}

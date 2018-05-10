package com.m.emad.madarsoft.view.utils;

import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.m.emad.madarsoft.R;


public class FragmentUtil {

    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace, String fragmentTag) {
        replaceFragment(FragmentManager, containerResId, fragmentToReplace, fragmentTag, -1, -1, true);
    }//end replaceFragment
    //===================================================================================//

    /**
     * same as {@link #replaceFragment(FragmentManager, int, Fragment, String)} with flag
     * to disable default animation
     *
     * @param runDefaultAnimation False to disable default transaction animation. True to keep it
     */
    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace, String fragmentTag,
                                       boolean runDefaultAnimation) {
        replaceFragment(FragmentManager, containerResId, fragmentToReplace, fragmentTag, -1, -1,
                runDefaultAnimation);
    }//end replaceFragment

    //===================================================================================//

    /**
     * Replaces fragment while adding it to back stack. using this method GUARANTEES
     * fragment is added to back stack. <br />
     * To add a fragment WITHOUT adding it to back stack, call {@link #replaceFragment(FragmentManager, int, Fragment, String)} <br />
     * This method performs fragment transaction with default component animation. To disable default animation,
     * use {@link #replaceFragment(FragmentManager, int, Fragment, boolean)}
     *
     * @param FragmentManager   manager used to perform transaction
     * @param containerResId    resource id where fragment should be placed
     * @param fragmentToReplace fragment to replace/display
     */
    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace) {
        replaceFragment(FragmentManager, containerResId, fragmentToReplace,
                fragmentToReplace.getClass().getName(), -1, -1, true);
    }//end replaceFragment
    //===================================================================================//

    /**
     * same as {@link #replaceFragment(FragmentManager, int, Fragment)} with flag to disable default
     * animation
     *
     * @param runDefaultAnimation False to disable default transaction animation. True to keep it
     */
    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace, boolean runDefaultAnimation) {
        replaceFragment(FragmentManager, containerResId, fragmentToReplace,
                fragmentToReplace.getClass().getName(), -1, -1, runDefaultAnimation);
    }//end replaceFragment
    //===================================================================================//

    /**
     * Replacing fragment into specified layout with specified animation. Replaced fragment is
     * added to back stack if a valid fragment tag is passed. If fragment tag is NULL, fragment is
     * not added to back stack. <br />
     * If no animation specified, transaction is done with default animation.
     *
     * @param FragmentManager     manager used to perform transaction
     * @param containerResId      resource id where fragment should be placed
     * @param fragmentToReplace   fragment to replace/display
     * @param fragmentTag         tag used to add fragment to back stack. When NULL, fragment is NOT added to stack
     * @param enterAnimationResId animation to apply when  fragment enters screen
     * @param exitAnimationResId  animation to display when fragment is leaving screen
     */
    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace, String fragmentTag,
                                       @AnimatorRes int enterAnimationResId,
                                       @AnimatorRes int exitAnimationResId) {

        replaceFragment(FragmentManager, containerResId, fragmentToReplace, fragmentTag,
                enterAnimationResId, exitAnimationResId, true);
    }//end replaceFragment
    //===================================================================================//

    /**
     * Replacing fragment into specified layout with specified animation. Replaced fragment is
     * added to back stack if a valid fragment tag is passed. If fragment tag is NULL, fragment is
     * not added to back stack. <br />
     * If no animation specified, transaction is done with default animation of : <br />
     * <li>enter animation: slide in right to left</li>
     * <li>exit animation: slide out right to left</li>
     * <li>Back animation: reverse enter and exit animations listed above</li>
     *
     * @param FragmentManager       manager used to perform transaction
     * @param containerResId        resource id where fragment should be placed
     * @param fragmentToReplace     fragment to replace/display
     * @param fragmentTag           tag used to add fragment to back stack. When NULL, fragment is NOT added to stack
     * @param enterAnimationResId   animation to apply when  fragment enters screen
     * @param exitAnimationResId    animation to display when fragment is leaving screen
     * @param applyDefaultAnimation flag indicating whether to run default fragment transaction animation or
     *                              disable it. False to disable default animation. True to run it
     */
    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace, String fragmentTag,
                                       @AnimatorRes int enterAnimationResId,
                                       @AnimatorRes int exitAnimationResId,
                                       boolean applyDefaultAnimation) {

        FragmentTransaction transaction = FragmentManager.beginTransaction();

        if (enterAnimationResId != -1 && exitAnimationResId != -1) {
            transaction.setCustomAnimations(enterAnimationResId, exitAnimationResId);
        }//end if --> animation supplied
        else {
            if (applyDefaultAnimation) {
                transaction.setCustomAnimations(R.animator.slide_in_right_to_left, R.animator.slide_out_right_to_left,
                        R.animator.slide_in_left_to_right, R.animator.slide_out_left_to_right);
                transaction.setCustomAnimations(R.anim.slide_in_right_to_left, R.anim.slide_out_right_to_left,
                        R.anim.slide_in_left_to_right, R.anim.slide_out_left_to_right);
            }//end if --> default animation can run
        }//end else --> no custom animation provided

        //adding transaction with tag to facilitate removal and retrieval of specific fragment from transactions list
        transaction.replace(containerResId, fragmentToReplace, fragmentToReplace.getClass().getName());

        if (fragmentTag != null) {
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }//end replaceFragment

    /**
     * Adds fragment to a container layout. fragment is added to back stack if tag != null
     *
     * @param FragmentManager   manager used to perform transaction
     * @param containerId       resource id where fragment should be placed
     * @param fragmentToBeAdded fragment to add/display
     */
    public static void addFragment(FragmentManager FragmentManager, int containerId, Fragment fragmentToBeAdded, int enterAnimationResId, int reVenterAnimationResId, int exitAnimationResId, int revExitAnimationResId) {
        FragmentTransaction FragmentTransaction = FragmentManager.beginTransaction();
        FragmentTransaction.add(containerId, fragmentToBeAdded, fragmentToBeAdded.getClass().getName());
        FragmentTransaction.addToBackStack(fragmentToBeAdded.getClass().getName());
        if (enterAnimationResId != -1 && exitAnimationResId != -1) {
            FragmentTransaction.setCustomAnimations(enterAnimationResId, reVenterAnimationResId, exitAnimationResId, revExitAnimationResId);
        }

        FragmentTransaction.commit();
    }

    public static void reAttachCurrentFragment(Fragment fragment) {

        FragmentManager currentFragmentManager;
        if (fragment.getParentFragment() != null) {
            currentFragmentManager = fragment.getParentFragment().getChildFragmentManager();
        } else {
            currentFragmentManager = fragment.getFragmentManager();
        }
        Fragment currentFragment = currentFragmentManager.findFragmentByTag(fragment.getClass().getName());
        FragmentTransaction fragTransaction = currentFragmentManager.beginTransaction();
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commitAllowingStateLoss();
    }

    public static Fragment getCurrentFragment(FragmentManager fragmentManager, int containerId) {
        return fragmentManager.findFragmentById(containerId);
    }
}
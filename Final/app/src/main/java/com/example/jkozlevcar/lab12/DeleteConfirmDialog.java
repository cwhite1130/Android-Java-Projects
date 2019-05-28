// Project:		Jiujitsu Technique Java III
// Class Name:	DeleteConfirmDialog
// Date:        3/5/19
// Author:      White

package com.example.jkozlevcar.lab12;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


// this class represents the missle dialog
public class DeleteConfirmDialog extends DialogFragment
{
    private String position;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get data from args
        position = getArguments().getString("name");

        // set the Message
        builder.setMessage(position + ": Confirm Delete");

        // set the title
        builder.setTitle("Delete");

        // set the positive button
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                System.out.println("positive " + position);
                // call the listener method in the main activity
                dListener.deleteDialogPositiveClick(position);
            }
        });

        // set the negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // User cancelled the dialog
                // call the listener method in the main activity
                dListener.deleteDialogNegativeClick();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface DeleteDialogListener
    {
        // these method will be implemented in the MainActivity
        public void deleteDialogPositiveClick(String position);
        public void deleteDialogNegativeClick();
    }

    // Use this instance of the interface to deliver action events
    private DeleteDialogListener dListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
//    public void onAttach(Activity activity) // is deprecated
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the DeleteDialogListener so we can send events to the host
            dListener = (DeleteDialogListener) context;
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DeleteDialogListener");
        }
    }
}

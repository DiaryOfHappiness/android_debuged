package com.example.happydiary;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.OnKeyListener;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceholderFragmentTwo extends Fragment {

	
	String title;
    public PlaceholderFragmentTwo(int id)
    {
    	switch(id)
    	{
    	case 1: title = "Что доставило мне радость сегодня?";
    		break;
    	case 2: title = "Кому и за что я сегодня благодарен?";
    		break;
    	case 3: title = "За что я благодарен себе?";
    		break;    	
    	}
    }

    ViewGroup mContainerView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_block, container, false);
       
        mContainerView = (ViewGroup) rootView.findViewById(R.id.event_layout2);
        ((TextView)(mContainerView.findViewById(R.id.event_title))).setText(title);
        
        addItem();
        
        return rootView;
    }
    
    
    
    private void addItem() {
        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
                R.layout.point_item, mContainerView, false);

        EditText newedit = (EditText) newView.findViewById(R.id.point_item_edit);
        newedit.setOnKeyListener(new EditKeyListener());
        
        newView.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	if(mContainerView.getChildCount() > 3)
            		mContainerView.removeView(newView);
            }
        });
        TextView tv = (TextView) mContainerView.findViewById(R.id.event_title);
        
        mContainerView.addView(newView);
    }
    
    private class EditKeyListener implements OnKeyListener{
    	@Override
    	public boolean onKey(View v, int keyCode, KeyEvent event) {
    		Toast.makeText(getActivity(), "Plus", 300).show();
			// TODO Auto-generated method stub
    		if(keyCode == KeyEvent.KEYCODE_ENTER)
    		{
    			if( v.equals(mContainerView.getChildAt( mContainerView.getChildCount()-1 ).findViewById(R.id.point_item_edit) ))
				{
    				PlaceholderFragmentTwo.this.addItem();
    				v.clearFocus();
    				return true;
				}
    		}
    		else if(keyCode == KeyEvent.KEYCODE_DEL)
    		{
    			if(mContainerView.getChildCount() > 2)
	    			if( ((EditText)v).getSelectionStart() == ((EditText)v).getSelectionEnd() &&  ((EditText)v).getSelectionEnd() == 0  )
	    				{
	    					ViewParent point_item = v.getParent();
	    					((ViewGroup)point_item.getParent()).removeView((View) point_item);
	    				}
    		}
			return false;
		}
	}
    
    
}
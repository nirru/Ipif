package com.oxilo.ipif.fragment;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oxilo.ipif.BaseDrawerActivity;
import com.oxilo.ipif.R;
import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.adapter.CartListAdapter;
import com.oxilo.ipif.adapter.ContactsListAdapter;
import com.oxilo.ipif.modal.Contacts;
import com.oxilo.ipif.modal.login.UserData;
import com.oxilo.ipif.util.HorizontalDividerItemDecoration;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import ir.mirrajabi.rxcontacts.Contact;
import ir.mirrajabi.rxcontacts.RxContacts;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ContactsListAdapter contactsListAdapter;
    RecyclerView recyler_view;

    ArrayList<Contact> contactList;

    UserData userData;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public ContactsFragment() {
        // Required empty public constructor
    }

    public void setUserData(UserData userData){
        this.userData = userData;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyler_view = (RecyclerView)view.findViewById(R.id.contacts);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClassRefrence();
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions
                .request(Manifest.permission.READ_CONTACTS)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) { // Always true pre-M
                            // I can control the camera now
                            getContacts();
                        } else {
                            // Oups permission denied
                            Toast.makeText(getActivity(),"app will not able to fetch contact",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void initClassRefrence() {
        contactList = new ArrayList<>();
                contactsListAdapter = new ContactsListAdapter(R.layout.contacts_row,contactList,getContext());
        LinearLayoutManager ll1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyler_view.setLayoutManager(ll1);
//        recyler_view.addItemDecoration(
//                new HorizontalDividerItemDecoration.Builder(getContext())
//                        .color(Color.LTGRAY)
//                        .sizeResId(R.dimen.divider)
//                        .marginResId(R.dimen.divider_left_margin, R.dimen.zero_margin)
//                        .build());
        recyler_view.setAdapter(contactsListAdapter);

        contactsListAdapter.setOnItemClickListener(new CartListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }



    private void getContacts(){
        RxContacts.fetch(getActivity())
                .filter(new Predicate<Contact>() {
                    @Override
                    public boolean test(@NonNull Contact m) throws Exception {
                        return m.getInVisibleGroup() == 1;
                    }
                })
                .toSortedList(new Comparator<Contact>() {
                    @Override
                    public int compare(Contact contact, Contact other) {
                        return contact.compareTo(other);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(@NonNull List<Contact> contacts) throws Exception {
                        for (Contact contact:contacts) {
                            contactsListAdapter.addItem(contact);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ((BaseDrawerActivity) getActivity()).setUpDrawable(toolbar);
    }
}

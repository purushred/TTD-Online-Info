package com.smart.ttddarshan.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.ECounterVO;

import java.util.ArrayList;

/**
 * Created by purushoy on 5/7/2015.
 */
public class ECounterAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ECounterVO> ecounterList;

    public ECounterAdapter(Context c) {
        mContext = c;
        populateECounters();
    }

    private void populateECounters() {

        ecounterList = new ArrayList<>();
        ECounterVO ecounterVO = new ECounterVO("City/Town", "State", "Address");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Adilabad", "Andhra Pradesh", "TTD Kalyanamandapam, e-Darshan Counter, Housing Board Colony, Adilabad, Adilabad District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Ahmedabad", "Gujarat", "Sri Balaji Temple, Opp. Nirma University, Ahmedabad");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Amalapuram", "Andhra Pradesh", "TTD Kalyana Mandapam e-Darshan Counter, East Godavari District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Amaravathi", "Maharashtra", "Raosaheb Shekhawat Mitra Mandal Building, Adjoining SBI Dastur Nagar Road Branch,Chattri Talab Road,Amravathi-444606.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Ananthapur", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Darshan Counter, Near Railway Station, Ramachandrapuram, Ananthaprur District.");

        ecounterVO = new ECounterVO("Bangalore 1 & 2", "Karnataka", "TTD Information Center, e-Darshan Counter, 16th Cross, Vyalikavel, Malleswaram, Banglore-3.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Bangalore 2", "Karnataka", "1360, Lakshmi Nivas- 32, e-Cross road, 4th T Block, Jaya Nagar Banglore.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Bapatla", "Andhra Pradesh", "TTD Kalyana Mandapam , e-Darshan Counter,Opp Kotha Bustand, Bapatla");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Bobbili", "Andhra Pradesh", "TTD Kalyana Mandapam, near water tank, Bobbili.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Bhimavaram", "Andhra Pradesh", "TTD Kalyana Mandapam e-Darshan Counter, Near Town Railwaystation, Bhimavaram-2 West Godavari District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Bhopal", "Madhya Pradesh", "The Andhra Sanskruthik Parishad,Link Road No.2.,Shivaji Nagar , Bhopal.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Bhubaneshwar", "Orissa", "TTD Kalyana Mandapam , e-Dharshan Counter, Jayadev vihar Chowk, Bhubaneshwar.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Chennai 1 & 2", "Tamil Nadu", "TTD Information Center,No:50, Venkatanarayana Road, T-Nagar, Chennai-70.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Chilakaluripeta", "Andhra Pradesh", "TTD e-Darshan Counter, Sri Venkateswara Swamy Aryavysya Kalyana Mandir Sangham, Guntur(Dt).");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Chirala", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Govt. Hospital, Kothapet, Chirala, Prakasam District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Chittoor", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Vellore Road, Chittoor District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Coimbatore", "Tamil Nadu", "TTD e-Dharshan Counter, Perumal Complex No.9, Papanaikampalayam, Coimbatore.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Erode", "Tamil Nadu", "e-Dharshan Counter, D/No: 315, Perundurai Road, Club Melange Campus, Erode-11.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Godavarikhani", "Andhra Pradesh", "T.T.D. E-Darshan Counter, New Ramagundam, Municipal Office Complex, Godavarikhani");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Guntur", "Andhra Pradesh", "TTD Information Centre/ Kalyanamandapam, e-Dharshan Counter, Rajagardens, Guntur, Guntur District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Hanumakonda", "Telangana", "TTD Kalyana Mandapam, e-Dharshan Counter, Kishanpoora, Hanumakonda, Warangal District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Hyderabad 1 , 2 & 3", "Telangana", "TTD Information Centre, e-Dharshan Counter, Balaji Bhavan, Liberty Circle, Opp Stanza complex, Himayat Nagar, Hyderabad.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Jaipur", "Rajasthan", "Andhra Association, F-47, Wajir Pur House,Sundar marg ,C-Scheme,Jaipur");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Jemshedpur", "Jharkhand", "e-Darshan Counter, Andhra Bhaktha Sree rama Mandiram, Bistpur, Jemshedpur - 831001");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kadapa", "Andhra Pradesh", "TTD Kalyana Mandapam e-Dharshan Counter, Madras Road, Sankarapuram, Kadapa, Kadapa District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kallakurichi", "Tamil Nadu", "Sri vari Spiritual and Developement Trust, Near Raja Theatre, Raja Nagar,Kallakurichi, Velli Puram Dist.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kakinada", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Near Balaji Cheruvu, Kakinada, East Godavari District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kandukur", "Andhra Pradesh", "TTD e-darshan counter, Skandapuri Jaganadha Swamy Devasthanam, Kandukur., Prakasam District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kanyakumari", "Tamil Nadu", "Vivekanadha Kendra, Vivekanada Puram, Kanyakumari.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Karimnagar", "Telangana", "TTD Kalyana Mandapam, e-Dharshan, Gandhi Road, Opp RTC Bus Stand, Karimnagar, Karimnagar District");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Khammam", "Telangana", "TTD Kalyanamandapam, e-Dharshan Counter, Opp RTC Bus Stand, Khammam, Khammam District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kharagpur", "West Bengal", "e-Dharshan Counter, Sri Sri Balaji Mandir Committe, Malamcha Road, Kharagpur.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kolhapur", "Maharastra", "e-Dharshan Counter, Sri Mahalakshmi Temple, Westran Maharastra Devasthanam management Committe, Chuorang Nagala park, Kolhapur.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kolkata", "West Bengal", "TTD e-Dharshan Counter, Andhra Association Bulding, 13A, Shah Nagar, Near Kalighat Station Rash Behari Avenue, Kolkata.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kuppam", "Andhra Pradesh", "TTD Kalyana Mandapam,e-Darshan Counter, Railway Court Road, Kuppam.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Kurnool", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, C-Camp Centre, Kurnool, Kurnool District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Latur", "Maharastra", "e-Dharshan Counter, AstaVainayaka Prathistan Mandir, Asta Vinayaka Prathistan Road, Latur.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Machilipatnam", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Near Bus Stand, Machilipatnam, Krishna District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Madurai", "Tamil Nadu", "TTD Information Centre, Tallakulam, Madurai");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Mahaboobnagar", "Telangana", "TTD Kalyana Mandapam, e-Dharshan Counter, Near Govt. Jr.College, New Town, Mahaboobnagar, MahaboobNagar District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Mamidikuduru", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Darshan Counter, NearSBI, Mamidikuduru.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Metpalli", "Telangana", "TTD Kalyana Mandapam, e-Darshan Counter, Near LIC Office,Metpalli, Karimnagar Dist.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Mumbai", "Maharastra", "Jainth House, 67 sion west, behind Vitobho temple, Sion circle, Mumbai-22");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Naidupet", "Andhra Predesh", "TTD Information Centere, e-Dharshan Counter,Ambedkar Building, Near Bus Stand, Naidupet, Nellore District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Nagapur", "Maharashtra", "Buldana Urban Coop. Bank, Ramadas Road, Nagapur.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Nalgonda", "Telangana", "TTD Kalyana Mandapam, e-Dharshan Counter, Shivaji Nagar, Nalgonda, Nalgonda District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Narasapuram", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Kovela Gudi Street,Narasapuram.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Nellore", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Durga Mitta, Nellore, Nellore District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("New Delhi", "New Delhi", "e-Dharshan Counter, A.P.Bhavan, No.1, Ashok Road, New Delhi 011-23385248.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Nizamabad", "Telangana", "TTD Kalyana Mandapam, e-Dharshan Counter, Pulan Chourastha, Vatani Road, Nizamabad. Nizamabad District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Ongole", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Santapet, Ongole, Prakasam District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Palakollu", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Housing Board Colony,Palakollu.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Piduguralla", "Andhra Pradesh", "e-Dharshan Counter, No.16-222, Behind ZP High School, Piduguralla, Guntur Dist.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Pondichery", "Pondichery", "TTD Information Centere, e-Darshan Counter, 288, J.N Street, Pondichery.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Proddatur", "Andhra Pradesh", "e-Dharshan Counter, TTD Kalyana Mandapam, Maidukuru Road, Proddatur, Kadapa District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Pulivendula", "Andhra pradesh", "TTD Information Centre, e-Darshan Counter, Muddanur Road, Pulivendula, Kadapa District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Pune", "Maharashtra", "M/s.SAPTAGIRI SHREE BALAJI SEVA TRUST, Survey no 70/1/1 B, Near Siciliaa, B.T.Kawade Road, Ghorpadi, Pune - 411001.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Puttaparthi", "Andhra Pradesh", "T.T.D. e-Darshan Counter, Near Sri Anjaneyaswamy Temple, Puttaparthi");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Rajamundry", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Danavaipet, Rajamundry, East Godavari District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Raipur", "Chattisghad", "Andhra Sanskritik Kala Samithi, Anmol flats, Avanthi vihar ,Raipur.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Rajapalam", "Tamil Nadu", "TTD Kalyana Mandapam, e-Dharshan Counter, 576, Tenkasi Road, Rajapalam.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Sanga Reddy", "Telangana", "TTD Kalyana Mandapam, e-Dharshan Counter, Sanga Reddy Medak District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Salem", "Tamil Nadu", "TTD Information Centere, e-Dharshan Counter, Sree Garuda Spirutal and Development Trust, 9/4C,6th Cross, Maravaneri, Salem 636007.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Shirdi", "Maharashtra", "Arya Vysya Nithyannadana Satram, opp to Joshi Hospital, Shirdi.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Sholapur", "Maharashtra", "Sri Venkateswara Devasthanam, Sri Venkateswara nagar, Dazi Pet, Sholapur.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Siddipeta", "Telangana", "TTD Kalyana Mandapam, e-Dharshan Counter, Beside Post office, Siddipeta, Medak District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Srikakulam", "Andhra Pradesh", "TTD Kalyanamandapam, e-Dharshan Counter, Near Gandhi Park, Srikakulam, Srikakulam Distict.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Srikalahasti", "Andhra Pradesh", "e-Dharshan Counter, Near by Srikalahasti Temple, Srikalahasti, Chittoor District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Tanuku", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Darshan Counter, R.P.Road, Opp. Reliance Mart, Tanuku.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Tadepalligudem", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Darshan Counter, Satyavathi Nagar, Tadepalligudem.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Thadipathri", "Andhra Pradesh", "TTD e-Darshan Counter, MLA JC Diwakar Reddy Complex, Opp. Municipality Office, Thadipathri.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Tirupur", "Tamil Nadu", "Srivari Trust, 241/1, DaraPuram Road, Tirupur-641601.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Trichi", "Tamil Nadu", "D-44,5th cross, North east extension,Thillai Nagar, Thiruchirapalli.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Tirupati", "Andhra Pradesh", "e-Darshan Counter,Srivari Sannidhi, Tirumala Bypass Road, Tirupati.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Tirupati", "Andhra Pradesh", "e-Darshan Counter, Mahati Auditorium Campus, Tirupati.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Udipi", "Karnataka", "Geetha Mandir, Srikrishna Temple, Udipi.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Varanasi", "Uttar Pradesh", "Kasi Annapurna Arya Vysya Nithyannadana Satram, Opp. Mazda Theatre, Varanasi.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Vellore", "Tamil Nadu", "TTD Information Centere e-Dharshan Counter, Officers Line, Vellore.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Vijayanagaram", "Andhra Pradesh", "TTD Kalyana mandapam, e-Dharshan Counter, Ranhjani Theatre, Vijayanagaram, Vijayanagaram District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Vijayawada", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, Punnama Thota, Near All India Radio, Vijayawada, Krishna District.");
        ecounterList.add(ecounterVO);
        ecounterVO = new ECounterVO("Visakhapatnam", "Andhra Pradesh", "TTD Kalyana Mandapam, e-Dharshan Counter, MVP Colony, Near Raithu Bazar, Visakapatnam, Visakapatnam District.");
        ecounterList.add(ecounterVO);

    }

    public int getCount() {
        return ecounterList.size();
    }

    public ECounterVO getItem(int position) {
        return ecounterList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            gridView = inflater.inflate(R.layout.ecounter_row, null);

        } else {
            gridView = convertView;
        }
        ECounterVO ecounterVO = getItem(position);
        TextView cityView = (TextView) gridView.findViewById(R.id.cityName);
        cityView.setText(ecounterVO.getCity());
        TextView address = (TextView) gridView.findViewById(R.id.address);
        address.setText(ecounterVO.getAddress());
        TextView state = (TextView) gridView.findViewById(R.id.state);
        state.setText(ecounterVO.getState());


        if (position == 0) {
            cityView.setTypeface(null, Typeface.BOLD);
            address.setTypeface(null, Typeface.BOLD);
            state.setTypeface(null, Typeface.BOLD);
        } else {

            cityView.setTypeface(null, Typeface.NORMAL);
            address.setTypeface(null, Typeface.NORMAL);
            state.setTypeface(null, Typeface.NORMAL);
        }
        return gridView;
    }
}

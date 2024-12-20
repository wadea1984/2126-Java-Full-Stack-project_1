import { useState, useEffect } from 'react';
import axios from 'axios';

const useStoreUserData = (url: string, _user: string, _pass: string
    ,_address: string,_phonenumber: string,_first:string, _last: string ) => {
  const [data, setData] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const postBody = { 
            username: _user,
            password: _pass,
            address:   _address,
            phone_number:_phonenumber,
            first_name:_first,
            last_name: _last,
            role: "employee"
           
           };

        console.log('Making API call to:', url, 'with body:', postBody);

        const response = await axios.post(url, postBody);
        setData(response.data);

        console.log('API Response:', response.data);
      } catch (err: any) {
        console.error('API Error:', err.message || err);
        setError('Failed to fetch data');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url, _user, _pass,_address,_phonenumber,_first,_last]);

  return { data, loading, error };
};

export default useStoreUserData 
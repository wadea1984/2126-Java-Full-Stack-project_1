import { useState, useEffect } from 'react';
import axios from 'axios';
import { useUserContext } from '../context/userContext';

const useStoreTicket = (url: string, _name: string, _amount: String
    ,_description: string ) => {
  const [data, setData] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { username, password,id } = useUserContext();
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const postBody = { 
            name: _name,
            amount: Number(_amount),
            description:_description,
            status: "pending",
            employeeID: id
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
  }, [url,_name,_amount,_description]);

  return { data, loading, error };
};

export default useStoreTicket
import React from 'react'

import { Link, useLocation } from "react-router-dom";
import { useUserContext } from '../context/userContext';

function NavBar() {
      const {role,logout}=useUserContext();
      const location = useLocation();

      if(location.pathname === '/'||location.pathname==='/Register') 
        return null

    return (
      <>
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
          <div className="container-fluid">
           
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  {role=="employee" &&
                  
                  <Link className="nav-link" to="/dashboard">
                    Dashboard
                  </Link>
                  }
                </li>
                <li className="nav-item">
                  {role=="employee" &&
                  <Link className="nav-link" to="/createTicket">
                    Create Ticket
                  </Link>
                }
                </li>
                <li className="nav-item">
                  {role=="employee" &&
                  <Link className="nav-link" to="/viewTickets">
                    View Tickets
                  </Link>
                  }
                </li>
                <li className="nav-item">
                  {role=="manager" &&
                  <Link className="nav-link" to="/TicketManager">
                    Process Tickets
                  </Link>
                   }
                </li>
                <li className="nav-item">
                  {role=="manager" &&
                  <Link className="nav-link" to="/dashboard">
                    Manager Dashboard
                  </Link>}
                  
                </li>
                <li className="nav-item">
                  <button className="nav-link" onClick={logout}>
                   Log out
                  </button>
                </li>
                
              </ul>
            </div>
          </div>
        </nav>
      </>
    );
  }
  
  export default NavBar;